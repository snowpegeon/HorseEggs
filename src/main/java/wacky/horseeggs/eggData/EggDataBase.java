/**
 * HorseEggs plugin.
 */

package wacky.horseeggs.eggData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
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
@Getter
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
  private final String dataKeyMinecraft = NamespacedKey.MINECRAFT + ":"; 

  private final String dataKeyChest = "Chest";
  private final String dataKeySpeed = "Speed";
  private final String dataKeyHealth = "Health";
  private final String dataKeyUuidLeast = "UUIDLeast";
  private final String dataKeyUuidMost = "UUIDMost";
  private final String dataKeyColor = "Color";
  private final String dataKeyJump = "Jump";
  private final String dataKeyMaxHealth = "MaxHealth";
  private final String dataKeyName = "Name";
  private final String dataKeyType = "Type";
  private final String dataKeyVariant = "Variant";
  private final String dataKeyArmor = "Armor";
  private final String dataKeyStyle = "Style";
  private final String dataKeySaddle = "Saddle";
  private final String dataKeyStrength = "Strength";

  @Setter
  private List<String> loreList;

  private Boolean isCarryingChest;
  /** ブロック上で移動する時の速度. */
  private Double speed;
  /** 現在体力. */
  private Double health;
  private Long uuidLeast;
  /** エンティティの色（ウマ、ラマ、行商人のラマ）. */
  private String color;
  /** 跳躍力. */
  private Double jump;
  /** 最大体力. */
  private Double maxHealth;
  /** 名札で付けた名前. */
  private String name;
  private Boolean isSaddled;
  /** Variant. */
  private String variant;
  /** エンティティタイプ. */
  private String type;
  private Long uuidMost;
  private String armor;
  /** 模様（ウマ）. */
  private String style;
  /** ？. */
  private Integer strength;
  private Map<String, ?> tagDataMap;
  private Map<String, ?> horseEggTagDataMap;

  private Map<String, ?> idNamespaceMap;
  private Map<String, ?> entityTagMap;

  private Map<String, ?> displayMap;
  
  private List<?> horseEggTagDataList;

  /**
   * Default constructor.
   *
   * @Deprecated
   *             <p>
   *             このコンストラクタは通常使用しないでください。<br>
   *             使用目的別で、各コンストラクタを呼び出してください。<br>
   *             キャプチャー：{@link EggDataBase#EggDataBase(AbstractHorse)}<br>
   *             リリース：{@link EggDataBase#EggDataBase(ItemStack)}
   *             </p>
   */
  public EggDataBase() {
    this.isCarryingChest = false;
    this.speed = 0d;
    this.health = 0d;
    this.uuidLeast = 0L;
    this.color = "";
    this.jump = 0d;
    this.maxHealth = 0d;
    this.name = null;
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
      // フィールドへ値をセット
      this.isCarryingChest = isCarryingChest(absHorse);
      this.speed = getSpeed(absHorse);
      this.health = getCurrentHealth(absHorse);
      this.uuidLeast = getUuidLeast(absHorse);
      this.color = getColor(absHorse);
      this.jump = getJump(absHorse);
      this.maxHealth = getMaxHealth(absHorse);
      this.name = getName(absHorse);
      this.isSaddled = isSaddled(absHorse);
      this.variant = getVariant(absHorse);
      this.type = getType(absHorse);
      this.uuidMost = getUuidMost(absHorse);
      this.armor = getArmor(absHorse);
      this.style = getStyle(absHorse);
      this.strength = getStrength(absHorse);

      // HorseEgg タグデータ構築
//      this.tagDataMap = Map.ofEntries(
//          Map.entry(dataKeyChest, isCarryingChest),
//          Map.entry(dataKeySpeed, speed),
//          Map.entry(dataKeyHealth, health),
//          Map.entry(dataKeyUuidLeast, uuidLeast),
//          Map.entry(dataKeyColor, color),
//          Map.entry(dataKeyJump, jump),
//          Map.entry(dataKeyMaxHealth, maxHealth),
//          Map.entry(dataKeyName, name),
//          Map.entry(dataKeySaddle, isSaddled),
//          Map.entry(dataKeyVariant, variant),
//          Map.entry(dataKeyType, type),
//          Map.entry(dataKeyUuidMost, uuidMost),
//          Map.entry(dataKeyArmor, armor),
//          Map.entry(dataKeyStyle, style),
//          Map.entry(dataKeyStrength, strength));
      this.tagDataMap = this.buildTagDataMap();
      this.horseEggTagDataMap = Map.of(EGG_NAME, tagDataMap);

      // EntityTag タグデータ構築
      this.idNamespaceMap = Map.of(dataKeyId, dataKeyMinecraft);
      this.entityTagMap = Map.of(dataKeyEntityTag, idNamespaceMap);

      // display タグデータ構築
      this.displayMap = Map.ofEntries(
          Map.entry(dataKeyLore, loreList),
          Map.entry(dataKeyName, name));

      // HorseEggs タグデータ構築
      this.horseEggTagDataList =
          List.of(this.displayMap, this.entityTagMap, this.horseEggTagDataMap);
    });
  }

  public EggDataBase(HashMap<String, ?> metaData) {
    Optional<HashMap<String, ?>> opt = Optional.ofNullable(metaData);
    opt.ifPresent(ah -> {
    });
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

  private Boolean isCarryingChest(AbstractHorse absHorse) {
    Boolean isCarryingChest = null;
    if (absHorse instanceof ChestedHorse chestedHorse) {
      isCarryingChest = chestedHorse.isCarryingChest();
    }
    return isCarryingChest;
  }

  private  Boolean getIsCarryingChest(HashMap<String, ?> metaData) {
    Byte hasChest = (Byte) metaData.get(dataKeyChest);
    return hasChest == 1;
  }

  private Double getSpeed(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
  }

  private Double getSpeed(HashMap<String, ?> metaData){
    Double speed = null;
    if(metaData.containsKey(dataKeySpeed)){
      speed = (Double) metaData.get(dataKeySpeed);
    }
    return speed;
  }

  private Double getCurrentHealth(AbstractHorse absHorse) {
    return absHorse.getHealth();
  }

  private Double getCurrentHealth(HashMap<String, ?> metaData){
    Double health = null;
    if(metaData.containsKey(dataKeyHealth)){
      health = (Double) metaData.get(dataKeyHealth);
    }
    return health;
  }

  private Long getUuidLeast(AbstractHorse absHorse) {
    Long uuidLeast = null;
    if (absHorse.isTamed()) {
      uuidLeast = absHorse.getOwner().getUniqueId().getLeastSignificantBits();
    }
    return uuidLeast;
  }

  private Long getUuidLeast(HashMap<String, ?> metaData){
    Long uuid = null;
    if(metaData.containsKey(dataKeyUuidLeast)){
      uuid = (Long) metaData.get(dataKeyUuidLeast);
    }
    return uuid;
  }

  private String getColor(AbstractHorse absHorse) {
    // NOTE: 実装をtoStringからnameに変更してる
    String color = null;
    if (absHorse instanceof Horse horse) {
      color = horse.getColor().name();
    } else if (absHorse instanceof Llama llama) {
      color = llama.getColor().name();
    }
    return color;
  }

  private String getColor(HashMap<String, ?> metaData){
    String color = null;
    if(metaData.containsKey(dataKeyColor)){
      color = (String) metaData.get(dataKeyColor);
    }
    return color;
  }

  private Double getJump(AbstractHorse absHorse) {
    return absHorse.getJumpStrength();
  }

  private Double getMaxHealth(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
  }

  private String getName(AbstractHorse absHorse) {
    String name = null;
    name = absHorse.getCustomName();
    return name;
  }

  private boolean isSaddled(AbstractHorse absHorse) {
    boolean hasSaddle = false;
    if (absHorse instanceof AbstractHorseInventory absHorseInv) {
      hasSaddle = Objects.nonNull(absHorseInv.getSaddle());
    }
    return hasSaddle;
  }

  private String getVariant(AbstractHorse absHorse) {
    // NOTE: 実装をtoStringからnameに変更してる
    String variant = null;
    if (Objects.nonNull(absHorse.getVariant())) {
      variant = absHorse.getVariant().name();
    }
    return variant;
  }

  private String getType(AbstractHorse absHorse) {
    String type = null;
    if (Objects.nonNull(absHorse.getType())) {
      type = absHorse.getType().toString();
    }
    return type;
  }

  private long getUuidMost(AbstractHorse absHorse) {
    Long uuidMost = null;
    if (absHorse.isTamed()) {
      uuidMost = absHorse.getOwner().getUniqueId().getMostSignificantBits();
    }
    return uuidMost;
  }

  private String getArmor(AbstractHorse absHorse) {
    String armor = null;
    if (absHorse instanceof AbstractHorseInventory absHorseInv) {
      ItemStack item = null;
      if (absHorseInv instanceof HorseInventory horseInv) {
        item = horseInv.getArmor();
      } else if (absHorse instanceof LlamaInventory llamaInv) {
        item = llamaInv.getDecor();
      }
      item.getType().name();
    }
    return armor;
  }

  private String getStyle(AbstractHorse absHorse) {
    String style = null;
    if (absHorse instanceof Horse horse) {
      style = horse.getStyle().name();
    }
    return style;
  }

  private Integer getStrength(AbstractHorse absHorse) {
    Integer strength = null;
    if (absHorse instanceof TraderLlama traderLlama) {
      strength = traderLlama.getStrength();
    } else if (absHorse instanceof Llama llama) {
      strength = llama.getStrength();
    }
    return strength;
  }

  private Map<String, ?> buildTagDataMap() {
    Map<String, Object> horseEggDataMap = new HashMap<>() {
      {
        if (Objects.nonNull(name)) {
          put(dataKeyName, name);
        }
        if (Objects.nonNull(health)) {
          put(dataKeyHealth, health);
        }
        if (Objects.nonNull(maxHealth)) {
          put(dataKeyMaxHealth, maxHealth);
        }
        if (Objects.nonNull(speed)) {
          put(dataKeySpeed, speed);
        }
        if (Objects.nonNull(jump)) {
          put(dataKeyJump, jump);
        }
        if (Objects.nonNull(type)) {
          put(dataKeyType, type);
        }
        if (Objects.nonNull(variant)) {
          put(dataKeyVariant, variant);
        }
        if (Objects.nonNull(strength)) {
          put(dataKeyStrength, strength);
        }
        if (Objects.nonNull(color)) {
          put(dataKeyColor, color);
        }
        if (Objects.nonNull(style)) {
          put(dataKeyStyle, style);
        }
        if (Objects.nonNull(uuidMost)) {
          put(dataKeyUuidMost, uuidMost);
        }
        if (Objects.nonNull(uuidLeast)) {
          put(dataKeyUuidLeast, uuidLeast);
        }
        if (Objects.nonNull(isSaddled)) {
          put(dataKeySaddle, isSaddled);
        }
        if (Objects.nonNull(armor)) {
          put(dataKeyArmor, armor);
        }
        if (Objects.nonNull(isCarryingChest)) {
          put(dataKeyChest, isCarryingChest);
        }
      }
    };


    return horseEggDataMap;
  }
}
