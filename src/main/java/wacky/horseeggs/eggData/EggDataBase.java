/**
 * HorseEggs plugin.
 */

package wacky.horseeggs.eggData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.BooleanUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * <p>
 * アイテムHorseEggの基底クラス.
 * </p>
 */
@Getter
public abstract class EggDataBase {

  /**
   * <p>
   * スポーンエッグをHorseEggと識別するキー. {@link String}
   * </p>
   */
  public static final String ENTITY_EGG_CONTENT_KEY = "EntityEgg";

  /**
   * <p>
   * HorseEggの中身を空と定義する値. {@link String}
   * </p>
   */
  public static final String ENTITY_EGG_CONTENT_EMPTY = "Empty";

  /**
   * <p>
   * HorseEggに付属する情報を引き出すためのNBTタグのキー. {@link String}
   * </p>
   */
  public static final String ENTITY_EGG_NBT_KEY = "EntityEgg_NBT";

  /**
   * <p>
   * HorseEggの同時出し入れ対策のためのタイムスタンプを取得するキー. {@link String}
   * </p>
   */
  public static final String ENTITY_EGG_TIMESTAMP_KEY = "EntityEggTimeStump";

  /**
   * <p>
   * HorseEggの表示名. {@link String}
   * </p>
   */
  public static final String EGG_NAME = "HorseEgg";

  /**
   * <p>
   * 対象のエンティティがインベントリを保持しているか. {@link boolean}
   * </p>
   */
  public boolean hasInventory = false;

  /**
   * <p>
   * Loreを出すときの1番目のデータキー. {@link String}
   * </p>
   */
  private final String dataKeyDisplay = "display";

  /**
   * <p>
   * Loreを出すときの2番目のデータキー. {@link String}
   * </p>
   */
  private final String dataKeyLore = "Lore";

  /**
   * <p>
   * EntityのIDを出すときの1番目のデータキー. {@link String}
   * </p>
   */
  private final String dataKeyEntityTag = "EntityTag";

  /**
   * <p>
   * EntityのIDを出すときの2番目のデータキー. {@link String}
   * </p>
   */
  private final String dataKeyId = "id";

  /**
   * <p>
   * minecraft: のnamespaceを出すための文字列. {@link String}
   * </p>
   */
  private final String dataKeyMinecraft = NamespacedKey.MINECRAFT + ":";

  /**
   * <p>
   * Chest有無のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyChest = "Chest";

  /**
   * <p>
   * 速さのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeySpeed = "Speed";

  /**
   * <p>
   * HPのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyHealth = "Health";

  /**
   * <p>
   * UUIDLeastのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyUuidLeast = "UUIDLeast";

  /**
   * <p>
   * UUIDMostのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyUuidMost = "UUIDMost";

  /**
   * <p>
   * 毛色データを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyColor = "Color";

  /**
   * <p>
   * ジャンプ高さのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyJump = "Jump";

  /**
   * <p>
   * 最大HPのデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyMaxHealth = "MaxHealth";

  /**
   * <p>
   * エンティティの名前のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyName = "Name";

  /**
   * <p>
   * エンティティの種別のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyType = "Type";

  /**
   * <p>
   * Variantデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyVariant = "Variant";

  /**
   * <p>
   * 鎧のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyArmor = "Armor";

  /**
   * <p>
   * 鎧の色データを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyArmorColor = "ArmorColor";

  /**
   * <p>
   * 模様のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyStyle = "Style";

  /**
   * <p>
   * 鞍の着脱のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeySaddle = "Saddle";

  /**
   * <p>
   * 収納領域のデータを取得するためのデータキー. {@link String}
   * </p>
   */
  private final String dataKeyStrength = "Strength";

  /**
   * <p>
   * ツールチップとして表示されるテキストのリスト. {@link List}
   * </p>
   */
  @Setter
  private List<String> loreList;

  /**
   * <p>
   * オーナー（テイムした人、プレイヤー名）. {@link String}
   * </p>
   */
  private String owner;

  /**
   * <p>
   * チェストが付いているか（ロバ、ラマ、ラマ、行商人のラマ）. {@link String}
   * </p>
   */
  private Boolean isCarryingChest;

  /**
   * <p>
   * ブロック上で移動する時の速度. {@link Double}
   * </p>
   */
  private Double speed;

  /**
   * <p>
   * 現在体力. {@link Double}
   * </p>
   */
  private Double health;

  /**
   * <p>
   * エンティティの色（ウマ、ラマ、行商人のラマ）. {@link String}
   * </p>
   */
  private String color;

  /**
   * <p>
   * 跳躍力. {@link Double}
   * </p>
   */
  private Double jump;

  /**
   * <p>
   * 最大体力. {@link Double}
   * </p>
   */
  private Double maxHealth;

  /**
   * <p>
   * 名札で付けた名前. {@link String}
   * </p>
   */
  private String name;

  /**
   * <p>
   * サドル（ウマ、ロバ、ラバ）. {@link Boolean}
   * </p>
   */
  private Boolean isSaddled;

  /**
   * <p>
   * Variant. {@link String}
   * </p>
   */
  private String variant;

  /**
   * <p>
   * エンティティタイプ. {@link String}
   * </p>
   */
  private String type;

  /**
   * <p>
   * テイムしたプレイヤーのUUID. {@link Long}
   * </p>
   */
  private Long uuidLeast;

  /**
   * <p>
   * テイムしたプレイヤーのUUID. {@link Long}
   * </p>
   */
  private Long uuidMost;

  /**
   * <p>
   * 装備（ウマ：鎧、ラマ：カーペット）. {@link String}
   * </p>
   */
  private String armor;

  /**
   * <p>
   * 模様（ウマ）. {@link String}
   * </p>
   */
  private String style;

  /**
   * <p>
   * 運搬力（ラマ、行商人のラマ）. {@link Integer}
   * </p>
   */
  private Integer strength;

  /**
   * <p>
   * 染色鎧の色. {@link String}
   * </p>
   */
  private String armorColor;

  /**
   * <p>
   * {@link #dataKeyId}タグにつけるデータマップの中身. {@link Map}
   * </p>
   */
  private Map<String, ?> tagDataMap;

  /**
   * <p>
   * {@link #dataKeyId}を直接埋め込むためのデータマップ. {@link Map}
   * </p>
   */
  private Map<String, ?> horseEggTagDataMap;

  /**
   * <p>
   * {@link #dataKeyId}を構築するMap. {@link Map}
   * </p>
   */
  private Map<String, ?> idNamespaceMap;

  /**
   * <p>
   * {@link #dataKeyEntityTag}を直接埋め込むためのデータマップ. {@link Map}
   * </p>
   */
  private Map<String, ?> entityTagMap;

  /**
   * <p>
   * {@link #dataKeyDisplay}を直接埋め込むためのデータマップ. {@link Map}
   * </p>
   */
  private Map<String, ?> displayMap;

  /**
   * <p>
   * HorseEggに関連するタグを直接埋め込むためのデータマップ. {@link List}
   * </p>
   */
  private List<?> horseEggTagDataList;

  /**
   * <p>
   * ArmorColorをタグに保持するためのフォーマット形式. {@link String}
   * </p>
   */
  public static final String ArmorColorFormat = "%d,%d,%d,%d";

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link EggDataBase#EggDataBase(AbstractHorse)}<br>
   *     リリース：{@link EggDataBase#EggDataBase(HashMap)}
   *     </p>
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
    this.armorColor = null;
    this.style = null;
    this.strength = null;
  }

  /**
   * {@link AbstractHorse}を利用したコンストラクタ.<br> 実体の馬からHorseEggを利用するために使用.
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
      this.armorColor = getArmorColor(absHorse);
      this.style = getStyle(absHorse);
      this.strength = getStrength(absHorse);

      this.owner = getOwner(absHorse);
      this.loreList = new ArrayList<>();

      // HorseEgg タグデータ構築
      this.tagDataMap = this.buildTagDataMap();
      this.horseEggTagDataMap = Map.of(EGG_NAME, tagDataMap);

      // EntityTag タグデータ構築
      this.idNamespaceMap = Map.of(dataKeyId, dataKeyMinecraft + type);
      this.entityTagMap = Map.of(dataKeyEntityTag, idNamespaceMap);

      // display タグデータ構築
      this.displayMap = new HashMap<>(){
        {
          put(dataKeyLore, loreList);
          if(Objects.nonNull(name)) {
            put(dataKeyName, name);
          }
        }
      };

      // HorseEggs タグデータ構築
      this.horseEggTagDataList =
          List.of(this.displayMap, this.entityTagMap, this.horseEggTagDataMap);
    });
  }

  /**
   * {@link HashMap}を利用したコンストラクタ.<br> HorseEggのアイテムから実体を作るときに使用.
   *
   * @param metaData {@link HashMap}
   */
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
      this.armorColor = getArmorColor(meta);
      this.style = getStyle(meta);
      this.strength = getStrength(meta);
    });
  }

  /**
   * 卵の表示名を取得.
   *
   * @return {@link #EGG_NAME}.
   */
  public String getDisplayName() {
    return EGG_NAME;
  }

  public EntityType getEmptyEggEntityType() {
    return EntityType.GHAST;
  }

  /**
   * 空の卵の表示に利用するMaterialを取得.
   *
   * @return {@link Material}.
   */
  public Material getEmptyEggMaterial() {
    return Material.GHAST_SPAWN_EGG;
  }

  /**
   * 該当する卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
  public abstract EntityType getEntityType();

  public abstract EntityType getFilledEggEntityType();

  public abstract Material getFilledEggMaterial();

  public Material getRecipeMaterialA() {
    return Material.ENDER_PEARL;
  }

  public Material getRecipeMaterialB() {
    return Material.EGG;
  }

  /**
   * {@link #dataKeyId}でタグに埋め込むデータを生成.
   *
   * @return {@link Map} {@link #dataKeyId}でタグに埋め込むデータ.
   */
  private Map<String, ?> buildTagDataMap() {
    return new HashMap<>() {
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
        if (Objects.nonNull(armorColor)) {
          put(dataKeyArmorColor, armorColor);
        }
        if (Objects.nonNull(isCarryingChest)) {
          put(dataKeyChest, isCarryingChest);
        }
      }
    };
  }

  /**
   * {@link AbstractHorse}から鎧データを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 鎧のアイテム文字列.
   */
  private String getArmor(AbstractHorse absHorse) {
    String armor = null;
    if (absHorse.getInventory() instanceof AbstractHorseInventory absHorseInv) {
      ItemStack item = null;
      if (absHorseInv instanceof HorseInventory horseInv) {
        item = horseInv.getArmor();
      } else if (absHorseInv instanceof LlamaInventory llamaInv) {
        item = llamaInv.getDecor();
      }
      if (Objects.nonNull(item)) {
        armor = item.getType().name();
      }
    }
    return armor;
  }

  /**
   * {@link HashMap}から鎧データを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} 鎧のアイテム文字列.
   */
  private String getArmor(HashMap<String, ?> metaData) {
    String armor = null;
    if (metaData.containsKey(dataKeyArmor)) {
      armor = (String) metaData.get(dataKeyArmor);
    }
    return armor;
  }

  /**
   * {@link AbstractHorse}から鎧の色データを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 鎧の色アイテム文字列.
   */
  private String getArmorColor(AbstractHorse absHorse) {
    String armorColor = null;
    if (absHorse.getInventory() instanceof HorseInventory horseInv) {
      ItemStack item = horseInv.getArmor();
      if (Objects.nonNull(item) && item.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta
          && Objects.nonNull(leatherArmorMeta.getColor())) {
        Color col = leatherArmorMeta.getColor();
        armorColor = String.format(ArmorColorFormat, col.getAlpha(), col.getRed(), col.getGreen(),
            col.getBlue());
      }
    }
    return armorColor;
  }

  /**
   * {@link HashMap}から鎧の色データを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} 鎧の色復元用文字列.
   */
  private String getArmorColor(HashMap<String, ?> metaData) {
    String armorColor = null;
    if (metaData.containsKey(dataKeyArmorColor)) {
      armorColor = (String) metaData.get(dataKeyArmorColor);
    }
    return armorColor;
  }

  /**
   * {@link AbstractHorse}から色データを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} エンティティの色.
   */
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

  /**
   * {@link HashMap}から色データを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} エンティティの色.
   */
  private String getColor(HashMap<String, ?> metaData) {
    String color = null;
    if (metaData.containsKey(dataKeyColor)) {
      color = (String) metaData.get(dataKeyColor);
    }
    return color;
  }

  /**
   * {@link AbstractHorse}からHPを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Double} HP.
   */
  private Double getHealth(AbstractHorse absHorse) {
    return absHorse.getHealth();
  }

  /**
   * {@link HashMap}からHPを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Double} HP.
   */
  private Double getHealth(HashMap<String, ?> metaData) {
    Double health = null;
    if (metaData.containsKey(dataKeyHealth)) {
      health = (Double) metaData.get(dataKeyHealth);
    }
    return health;
  }

  /**
   * {@link AbstractHorse}から跳躍力を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Double} 跳躍力.
   */
  private Double getJump(AbstractHorse absHorse) {
    return absHorse.getJumpStrength();
  }

  /**
   * {@link HashMap}から跳躍力を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Double} 跳躍力.
   */
  private Double getJump(HashMap<String, ?> metaData) {
    Double jump = null;
    if (metaData.containsKey(dataKeyJump)) {
      jump = (Double) metaData.get(dataKeyJump);
    }
    return jump;
  }

  /**
   * {@link AbstractHorse}から最大HPを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Double} 最大HP.
   */
  private Double getMaxHealth(AbstractHorse absHorse) {
    AttributeInstance maxHealth = absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    Double health = null;
    if (Objects.nonNull(maxHealth)) {
      health = maxHealth.getValue();
    }
    return health;
  }

  /**
   * {@link HashMap}から最大HPを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Double} 最大HP.
   */
  private Double getMaxHealth(HashMap<String, ?> metaData) {
    Double maxHealth = null;
    if (metaData.containsKey(dataKeyMaxHealth)) {
      maxHealth = (Double) metaData.get(dataKeyMaxHealth);
    }
    return maxHealth;
  }

  /**
   * {@link AbstractHorse}からエンティティの名前を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 名前.
   */
  private String getName(AbstractHorse absHorse) {
    return absHorse.getCustomName();
  }

  /**
   * {@link HashMap}からエンティティの名前を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} 名前.
   */
  private String getName(HashMap<String, ?> metaData) {
    String name = null;
    if (metaData.containsKey(dataKeyName)) {
      name = (String) metaData.get(dataKeyName);
    }
    return name;
  }

  /**
   * {@link AbstractHorse}からエンティティの飼い主名を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 飼い主名.
   */
  private String getOwner(AbstractHorse absHorse) {
    String owner = null;
    if (Objects.nonNull(absHorse.getOwner())) {
      owner = absHorse.getOwner().getName();
    }
    return owner;
  }

  /**
   * {@link AbstractHorse}から速度を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Double} 速度.
   */
  private Double getSpeed(AbstractHorse absHorse) {
    Double speed = null;
    AttributeInstance speedInstance = absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
    if (Objects.nonNull(speedInstance)) {
      speed = speedInstance.getBaseValue();
    }
    return speed;
  }

  /**
   * {@link HashMap}から速度を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Double} 速度.
   */
  private Double getSpeed(HashMap<String, ?> metaData) {
    Double speed = null;
    if (metaData.containsKey(dataKeySpeed)) {
      speed = (Double) metaData.get(dataKeySpeed);
    }
    return speed;
  }

  /**
   * {@link AbstractHorse}からエンティティの運搬力を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Integer} 運搬力.
   */
  private Integer getStrength(AbstractHorse absHorse) {
    Integer strength = null;
    if (absHorse instanceof TraderLlama traderLlama) {
      strength = traderLlama.getStrength();
    } else if (absHorse instanceof Llama llama) {
      strength = llama.getStrength();
    }
    return strength;
  }

  /**
   * {@link HashMap}からエンティティの運搬力を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Integer} 運搬力.
   */
  private Integer getStrength(HashMap<String, ?> metaData) {
    Integer strength = null;
    if (metaData.containsKey(dataKeyStrength)) {
      strength = (Integer) metaData.get(dataKeyStrength);
    }
    return strength;
  }

  /**
   * {@link AbstractHorse}から模様を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 模様.
   */
  private String getStyle(AbstractHorse absHorse) {
    String style = null;
    if (absHorse instanceof Horse horse) {
      style = horse.getStyle().name();
    }
    return style;
  }

  /**
   * {@link HashMap}から模様を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} 模様.
   */
  private String getStyle(HashMap<String, ?> metaData) {
    String style = null;
    if (metaData.containsKey(dataKeyStyle)) {
      style = (String) metaData.get(dataKeyStyle);
    }
    return style;
  }

  /**
   * {@link AbstractHorse}からエンティティの種類を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} 種類.
   */
  private String getType(AbstractHorse absHorse) {
    return absHorse.getType().toString();
  }

  /**
   * {@link HashMap}からエンティティの種類を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} 種類.
   */
  @Nullable
  private String getType(HashMap<String, ?> metaData) {
    String type = null;
    if (metaData.containsKey(dataKeyType)) {
      type = (String) metaData.get(dataKeyType);
    }
    return type;
  }

  /**
   * {@link AbstractHorse}からエンティティの飼い主のUUIDLeastを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Long} 飼い主のUUIDLeast.
   */
  @Nullable
  private Long getUuidLeast(AbstractHorse absHorse) {
    Long uuidLeast = null;
    if (absHorse.isTamed()) {
      AnimalTamer owner = absHorse.getOwner();
      if (Objects.nonNull(owner)) {
        uuidLeast = owner.getUniqueId().getLeastSignificantBits();
      }
    }
    return uuidLeast;
  }

  /**
   * {@link HashMap}からエンティティの飼い主のUUIDLeastを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Long} 飼い主のUUIDLeast.
   */
  @Nullable
  private Long getUuidLeast(HashMap<String, ?> metaData) {
    Long uuid = null;
    if (metaData.containsKey(dataKeyUuidLeast)) {
      uuid = (Long) metaData.get(dataKeyUuidLeast);
    }
    return uuid;
  }

  /**
   * {@link AbstractHorse}からエンティティの飼い主のUUIDMostを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Long} 飼い主のUUIDMost.
   */
  @Nullable
  private Long getUuidMost(AbstractHorse absHorse) {
    Long uuidMost = null;
    if (absHorse.isTamed()) {
      AnimalTamer owner = absHorse.getOwner();
      if (Objects.nonNull(owner)) {
        uuidMost = owner.getUniqueId().getMostSignificantBits();
      }
    }
    return uuidMost;
  }

  /**
   * {@link HashMap}からエンティティの飼い主のUUIDMostを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Long} 飼い主のUUIDMost.
   */
  @Nullable
  private Long getUuidMost(HashMap<String, ?> metaData) {
    Long uuidMost = null;
    if (metaData.containsKey(dataKeyUuidMost)) {
      uuidMost = (long) metaData.get(dataKeyUuidMost);
    }
    return uuidMost;
  }

  /**
   * {@link AbstractHorse}からVariantを取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link String} Variant.
   * @deprecated このメソッドは旧式データの取得のため残しているものです.<br> 通常は使わないでください.
   */
  private String getVariant(AbstractHorse absHorse) {
    // NOTE: 実装をtoStringからnameに変更してる
    return absHorse.getVariant().name();
  }

  /**
   * {@link HashMap}からVariantを取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link String} Variant.
   * @deprecated このメソッドは旧式データの取得のため残しているものです.<br> 通常は使わないでください.
   */
  @Nullable
  private String getVariant(HashMap<String, ?> metaData) {
    String variant = null;
    if (metaData.containsKey(dataKeyVariant)) {
      variant = (String) metaData.get(dataKeyVariant);
    }
    return variant;
  }

  /**
   * {@link AbstractHorse}からチェストの有無を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link Boolean} チェストの有無.
   */
  @Nullable
  private Boolean isCarryingChest(AbstractHorse absHorse) {
    Boolean isCarryingChest = null;
    if (absHorse instanceof ChestedHorse chestedHorse) {
      isCarryingChest = chestedHorse.isCarryingChest();
    }
    return isCarryingChest;
  }

  /**
   * {@link HashMap}からチェストの有無を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link Boolean} チェストの有無.
   */
  private Boolean isCarryingChest(HashMap<String, ?> metaData) {
    Byte hasChest = null;
    if(metaData.containsKey(dataKeyChest)){
      hasChest = (Byte) metaData.get(dataKeyChest);
    }
    return Objects.nonNull(hasChest) && hasChest == 1;
  }

  /**
   * {@link AbstractHorse}から鞍の有無を取得.
   *
   * @param absHorse {@link AbstractHorse} エンティティ本体.
   * @return {@link boolean} 鞍の有無.
   */
  private boolean isSaddled(AbstractHorse absHorse) {
    boolean hasSaddle = false;
    if (absHorse.getInventory() instanceof AbstractHorseInventory absHorseInv) {
      hasSaddle = Objects.nonNull(absHorseInv.getSaddle());
    }
    return hasSaddle;
  }

  /**
   * {@link HashMap}から鞍の有無を取得.
   *
   * @param metaData {@link HashMap} 卵のItemStackから取得されたメタデータ.
   * @return {@link boolean} 鞍の有無.
   */
  private boolean isSaddled(HashMap<String, ?> metaData) {
    boolean hasSaddle = false;
    if (metaData.containsKey(dataKeySaddle)) {
      if (metaData.get(dataKeySaddle) instanceof Byte isSaddledByte) {
        hasSaddle = BooleanUtils.toBoolean(isSaddledByte.intValue());
      }
    }
    return hasSaddle;
  }
}
