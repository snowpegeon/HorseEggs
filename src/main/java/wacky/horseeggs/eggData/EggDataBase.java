/**
 * HorseEggs plugin.
 */

package wacky.horseeggs.eggData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.TraderLlama;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;

/**
 * Base class for HorseEgg Data.
 */
@Data
public abstract class EggDataBase {
  public static final String ENTITYEGG_CONTENT_KEY = "EntityEgg";
  public static final String ENTITYEGG_CONTENT_EMPTY = "Empty";
  public static final String ENTITYEGG_NBT_KEY = "EntityEgg_NBT";
  public static final String ENTITYEGG_TIMESTAMP_KEY = "EntityEggTimeStump";

  public static final String EGG_NAME = "HorseEgg";

  public boolean hasInventory = false;

  private final String dataKeyDisplay = "display";
  private final String dataKeyLore = "Lore";

  private final String dataKeyEntityTag = "EntityTag";
  private final String dataKeyId = "id";
  private final String dataKeyMinecraft = "minecraft:";

  private final String dataKeyChest = "Chest";
  private final String dataKeySpeed = "Speed";
  private final String dataKeyHealth = "Health";
  private final String dataKeyUuidLeast = "UUIDLeast";
  private final String dataKeyUuidMost = "UUIDMost";
  private final String dataKeyColor = "Color";
  private final String dataKeyJump = "Jump";
  private final String dataKeyMaxHealth = "MaxHealth";
  private final String dataKeyType = "Type";
  private final String dataKeyVariant = "Variant";
  private final String dataKeyArmor = "Armor";
  private final String dataKeyStyle = "Style";
  private final String dataKeySaddle = "Saddle";
  private final String dataKeyStrength = "Strength";

  private List<String> loreList;

  private boolean isCarryingChest;
  private double speed;
  private double currentHealth;
  private long uuidLeast;
  private String color;
  private double jump;
  private double maxHealth;
  private boolean isSaddled;
  private String variant;
  private String type;
  private long uuidMost;
  private String armor;
  private String style;
  private int strength;
  private Map<String, Object> tagDataMap;
  private Map<String, Object> horseEggTagDataMap;

  private Map<String, String> idNamespaceMap;
  private Map<String, Object> entityTagMap;

  /** Default constructor. */
  public EggDataBase() {
    this.isCarryingChest = false;
    this.speed = 0d;
    this.currentHealth = 0d;
    this.uuidLeast = 0L;
    this.color = "";
    this.jump = 0d;
    this.maxHealth = 0d;
    this.isSaddled = false;
    this.variant = "";
    this.type = "";
    this.uuidMost = 0L;
    this.armor = "";
    this.style = "";
    this.strength = 0;
  }

  /**
   * Constructor for abstract horse entity.
   *
   * @param absHorse {@link AbstractHorse}
   */
  public EggDataBase(AbstractHorse absHorse) {
    this();

    Optional<AbstractHorse> opt = Optional.ofNullable(absHorse);
    opt.ifPresent(ah -> {
      this.isCarryingChest = isCarryingChest(absHorse);
      this.speed = getSpeed(absHorse);
      this.currentHealth = getCurrentHealth(absHorse);
      this.uuidLeast = getUuidLeast(absHorse);
      this.color = getColor(absHorse);
      this.jump = getJump(absHorse);
      this.maxHealth = getMaxHealth(absHorse);
      this.isSaddled = isSaddled(absHorse);
      this.variant = getVariant(absHorse);
      this.type = getType(absHorse);
      this.uuidMost = getUuidMost(absHorse);
      this.armor = getArmor(absHorse);
      this.style = getStyle(absHorse);
      this.strength = getStrength(absHorse);

      this.tagDataMap = new HashMap<>() {
        {
          put(dataKeyChest, isCarryingChest);
          put(dataKeySpeed, speed);
          put(dataKeyHealth, currentHealth);
          put(dataKeyUuidLeast, uuidLeast);
          put(dataKeyColor, color);
          put(dataKeyJump, jump);
          put(dataKeyMaxHealth, maxHealth);
          put(dataKeySaddle, isSaddled);
          put(dataKeyVariant, variant);
          put(dataKeyType, type);
          put(dataKeyUuidMost, uuidMost);
          put(dataKeyArmor, armor);
          put(dataKeyStyle, style);
          put(dataKeyStrength, strength);
        }
      };
      this.horseEggTagDataMap = new HashMap<>() {
        {
          put(EGG_NAME, tagDataMap);
        }
      };

      this.idNamespaceMap = new HashMap<>() {
        {
          put(dataKeyId, dataKeyMinecraft);
        }
      };
      this.entityTagMap = new HashMap<>() {
        {
          put(dataKeyEntityTag, idNamespaceMap);
        }
      };
    });
  }

  public EggDataBase(ItemStack item) {
    // TODO: item から変換
  }

  public abstract EntityType getEntityType();

  public abstract EntityType getFilledEggEntityType();

  public abstract Material getFilledEggMaterial();

  public Material getEmptyEggMaterial() {
    return Material.GHAST_SPAWN_EGG;
  }

  public EntityType getEmptyEggEntityType() {
    return EntityType.GHAST;
  }

  public String getDisplayName() {
    return EGG_NAME;
  }

  public Material getRecipeMaterialA() {
    return Material.ENDER_PEARL;
  }

  public Material getRecipeMaterialB() {
    return Material.EGG;
  }

  private boolean isCarryingChest(AbstractHorse absHorse) {
    boolean hasChest = false;
    if (absHorse instanceof ChestedHorse chestedHorse) {
      hasChest = chestedHorse.isCarryingChest();
    }
    return hasChest;
  }

  private double getSpeed(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
  }

  private double getCurrentHealth(AbstractHorse absHorse) {
    return absHorse.getHealth();
  }

  private long getUuidLeast(AbstractHorse absHorse) {
    long uuidLeast = 0L;
    if (absHorse.getOwner() instanceof AnimalTamer animalTamer) {
      uuidLeast = animalTamer.getUniqueId().getLeastSignificantBits();
    }
    return uuidLeast;
  }

  private String getColor(AbstractHorse absHorse) {
    String color = "";
    if (absHorse instanceof Horse horse) {
      color = horse.getColor().name();
    } else if (absHorse instanceof Llama llama) {
      color = llama.getColor().name();
    }
    return color;
  }

  private double getJump(AbstractHorse absHorse) {
    return absHorse.getJumpStrength();
  }

  private double getMaxHealth(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
  }

  private boolean isSaddled(AbstractHorse absHorse) {
    boolean hasSaddle = false;
    if (absHorse instanceof AbstractHorseInventory absHorseInv) {
      hasSaddle = Objects.nonNull(absHorseInv.getSaddle());
    }
    return hasSaddle;
  }

  private String getVariant(AbstractHorse absHorse) {
    return absHorse.getVariant().name();
  }

  private String getType(AbstractHorse absHorse) {
    return absHorse.getType().toString();
  }

  private long getUuidMost(AbstractHorse absHorse) {
    long uuidMost = 0L;
    if (absHorse instanceof AnimalTamer animalTamer) {
      uuidMost = animalTamer.getUniqueId().getMostSignificantBits();
    }
    return uuidMost;
  }

  private String getArmor(AbstractHorse absHorse) {
    String armor = "";
    if (absHorse instanceof AbstractHorseInventory absHorseInv) {
      ItemStack item = null;
      if (absHorseInv instanceof HorseInventory horseInv) {
        item = horseInv.getArmor();
      } else if (absHorse instanceof LlamaInventory llamaInv) {
        item = llamaInv.getDecor();
      }
      if (Objects.nonNull(item)) {
        armor = item.getType().name();
      }
    }
    return armor;
  }

  private String getStyle(AbstractHorse absHorse) {
    String style = "";
    if (absHorse instanceof Horse horse) {
      style = horse.getStyle().name();
    }
    return style;
  }

  private int getStrength(AbstractHorse absHorse) {
    int strength = 0;
    if (absHorse instanceof Llama llama) {
      strength = llama.getStrength();
    } else if (absHorse instanceof TraderLlama traderLlama) {
      strength = traderLlama.getStrength();
    }
    return strength;
  }
}
