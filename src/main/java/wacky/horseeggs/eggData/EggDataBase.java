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
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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

  /** ツールチップとして表示されるテキストのリスト. */
  @Setter
  private List<String> loreList;
  /** テイムした人、オーナー（プレイヤー名）. */
  private String owner;
  /** チェストが付いているか（ロバ、ラマ、ラマ、行商人のラマ）. */
  private Boolean isCarryingChest;
  /** ブロック上で移動する時の速度. */
  private Double speed;
  /** 現在体力. */
  private Double health;
  /** エンティティの色（ウマ、ラマ、行商人のラマ）. */
  private String color;
  /** 跳躍力. */
  private Double jump;
  /** 最大体力. */
  private Double maxHealth;
  /** 名札で付けた名前. */
  private String name;
  /** サドル（ウマ、ロバ、ラバ）. */
  private Boolean isSaddled;
  /** Variant. */
  private String variant;
  /** エンティティタイプ. */
  private String type;
  /** テイムしたプレイヤーのUUID. */
  private Long uuidLeast;
  /** テイムしたプレイヤーのUUID. */
  private Long uuidMost;
  /** 装備（ウマ：鎧、ラマ：カーペット）. */
  private String armor;
  /** 模様（ウマ）. */
  private String style;
  /** 運搬力（ラマ、行商人のラマ）. */
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
    this.isCarryingChest = null;
    this.speed = null;
    this.health = null;
    this.uuidLeast = null;
    this.color = null;
    this.jump = null;
    this.maxHealth = null;
    this.name = null;
    this.isSaddled = null;
    this.variant = null;
    this.type = null;
    this.uuidMost = null;
    this.armor = null;
    this.style = null;
    this.strength = null;
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
      this.health = getHealth(absHorse);
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

      this.owner = getOwner(absHorse);
      
      // HorseEgg タグデータ構築
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
    this();

    Optional<HashMap<String, ?>> opt = Optional.ofNullable(metaData);
    opt.ifPresent(meta -> {
      // フィールドへ値をセット
      this.isCarryingChest = isCarryingChest(meta);
      this.speed = getSpeed(meta);
      this.health = getHealth(meta);
      this.uuidLeast = getUuidLeast(meta);
      this.color = getColor(meta);
      this.jump = getJump(meta);
      this.maxHealth = getMaxHealth(meta);
      this.name = getName(meta);
      this.isSaddled = isSaddled(meta);
      this.variant = getVariant(meta);
      this.type = getType(meta);
      this.uuidMost = getUuidMost(meta);
      this.armor = getArmor(meta);
      this.style = getStyle(meta);
      this.strength = getStrength(meta);
    });
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

  private String getArmor(HashMap<String, ?> metaData) {
    String armor = null;
    if (metaData.containsKey(dataKeyArmor)) {
      armor = (String) metaData.get(dataKeyArmor);
    }
    return armor;
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

  private String getColor(HashMap<String, ?> metaData) {
    String color = null;
    if (metaData.containsKey(dataKeyColor)) {
      color = (String) metaData.get(dataKeyColor);
    }
    return color;
  }

  public String getDisplayName() {
    return EGG_NAME;
  }

  public EntityType getEmptyEggEntityType() {
    return EntityType.GHAST;
  }

  public Material getEmptyEggMaterial() {
    return Material.GHAST_SPAWN_EGG;
  }

  public abstract EntityType getEntityType();

  public abstract EntityType getFilledEggEntityType();

  public abstract Material getFilledEggMaterial();

  private Double getHealth(AbstractHorse absHorse) {
    return absHorse.getHealth();
  }

  private Double getHealth(HashMap<String, ?> metaData) {
    Double health = null;
    if (metaData.containsKey(dataKeyHealth)) {
      health = (Double) metaData.get(dataKeyHealth);
    }
    return health;
  }

  private Double getJump(AbstractHorse absHorse) {
    return absHorse.getJumpStrength();
  }

  private Double getJump(HashMap<String, ?> metaData) {
    Double jump = null;
    if (metaData.containsKey(dataKeyJump)) {
      jump = (Double) metaData.get(dataKeyJump);
    }
    return jump;
  }

  private Double getMaxHealth(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
  }

  private Double getMaxHealth(HashMap<String, ?> metaData) {
    Double maxHealth = null;
    if (metaData.containsKey(dataKeyMaxHealth)) {
      maxHealth = (Double) metaData.get(dataKeyMaxHealth);
    }
    return maxHealth;
  }

  private String getName(AbstractHorse absHorse) {
    String name = null;
    name = absHorse.getCustomName();
    return name;
  }

  private String getName(HashMap<String, ?> metaData) {
    String name = null;
    if (metaData.containsKey(dataKeyName)) {
      name = (String) metaData.get(dataKeyName);
    }
    return name;
  }

  private String getOwner(AbstractHorse absHorse) {
    String owner = null;
    if (absHorse.getOwner() instanceof AnimalTamer animalTamer) {
      owner = animalTamer.getName();
    }
    return owner;
  }

  public Material getRecipeMaterialA() {
    return Material.ENDER_PEARL;
  }

  public Material getRecipeMaterialB() {
    return Material.EGG;
  }

  private Double getSpeed(AbstractHorse absHorse) {
    return absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
  }

  private Double getSpeed(HashMap<String, ?> metaData) {
    Double speed = null;
    if (metaData.containsKey(dataKeySpeed)) {
      speed = (Double) metaData.get(dataKeySpeed);
    }
    return speed;
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

  private Integer getStrength(HashMap<String, ?> metaData) {
    Integer strength = null;
    if (metaData.containsKey(dataKeyStyle)) {
      strength = (Integer) metaData.get(dataKeyStyle);
    }
    return strength;
  }

  private String getStyle(AbstractHorse absHorse) {
    String style = null;
    if (absHorse instanceof Horse horse) {
      style = horse.getStyle().name();
    }
    return style;
  }

  private String getStyle(HashMap<String, ?> metaData) {
    String style = null;
    if (metaData.containsKey(dataKeyStyle)) {
      style = (String) metaData.get(dataKeyStyle);
    }
    return style;
  }

  private String getType(AbstractHorse absHorse) {
    String type = null;
    if (Objects.nonNull(absHorse.getType())) {
      type = absHorse.getType().toString();
    }
    return type;
  }

  private String getType(HashMap<String, ?> metaData) {
    String type = null;
    if (metaData.containsKey(dataKeyType)) {
      type = (String) metaData.get(dataKeyType);
    }
    return type;
  }

  private Long getUuidLeast(AbstractHorse absHorse) {
    Long uuidLeast = null;
    if (absHorse.isTamed()) {
      uuidLeast = absHorse.getOwner().getUniqueId().getLeastSignificantBits();
    }
    return uuidLeast;
  }

  private Long getUuidLeast(HashMap<String, ?> metaData) {
    Long uuid = null;
    if (metaData.containsKey(dataKeyUuidLeast)) {
      uuid = (Long) metaData.get(dataKeyUuidLeast);
    }
    return uuid;
  }

  private long getUuidMost(AbstractHorse absHorse) {
    Long uuidMost = null;
    if (absHorse.isTamed()) {
      uuidMost = absHorse.getOwner().getUniqueId().getMostSignificantBits();
    }
    return uuidMost;
  }

  private long getUuidMost(HashMap<String, ?> metaData) {
    Long uuidMost = null;
    if (metaData.containsKey(dataKeyUuidMost)) {
      uuidMost = (long) metaData.get(dataKeyUuidMost);
    }
    return uuidMost;
  }

  private String getVariant(AbstractHorse absHorse) {
    // NOTE: 実装をtoStringからnameに変更してる
    String variant = null;
    if (Objects.nonNull(absHorse.getVariant())) {
      variant = absHorse.getVariant().name();
    }
    return variant;
  }

  private String getVariant(HashMap<String, ?> metaData) {
    String variant = null;
    if (metaData.containsKey(dataKeyVariant)) {
      variant = (String) metaData.get(dataKeyVariant);
    }
    return variant;
  }

  private Boolean isCarryingChest(AbstractHorse absHorse) {
    Boolean isCarryingChest = null;
    if (absHorse instanceof ChestedHorse chestedHorse) {
      isCarryingChest = chestedHorse.isCarryingChest();
    }
    return isCarryingChest;
  }

  private  Boolean isCarryingChest(HashMap<String, ?> metaData) {
    Byte hasChest = (Byte) metaData.get(dataKeyChest);
      return hasChest == 1;
  }

  private boolean isSaddled(AbstractHorse absHorse) {
    boolean hasSaddle = false;
    if (absHorse instanceof AbstractHorseInventory absHorseInv) {
      hasSaddle = Objects.nonNull(absHorseInv.getSaddle());
    }
    return hasSaddle;
  }

  private boolean isSaddled(HashMap<String, ?> metaData) {
    boolean hasSaddle = false;
    if (metaData.containsKey(dataKeySaddle)) {
      hasSaddle = (boolean) metaData.get(dataKeySaddle);
    }
    return hasSaddle;
  }
}
