package wacky.horseeggs.LoreWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.LoreWriter.factory.LoreWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * <p>
 * {@link LoreWriter} クラスを検証するテストクラスです.
 * </p>
 */
public class LoreWriterTest {

  static final String dataKeyChest = "Chest";
  static final String dataKeySpeed = "Speed";
  static final String dataKeyHealth = "Health";
  static final String dataKeyUuidLeast = "UUIDLeast";
  static final String dataKeyUuidMost = "UUIDMost";
  static final String dataKeyColor = "Color";
  static final String dataKeyJump = "Jump";
  static final String dataKeyMaxHealth = "MaxHealth";
  static final String dataKeyName = "Name";
  static final String dataKeyType = "Type";
  static final String dataKeyVariant = "Variant";
  static final String dataKeyArmor = "Armor";
  static final String dataKeyStyle = "Style";
  static final String dataKeySaddle = "Saddle";
  static final String dataKeyStrength = "Strength";
  static final String dataKeyOwner = "Owner";

  static final String valueSplitter = "/";

  static final String itemPrefix = "[";
  static final String itemSuffix = "]";
  static final String itemSplitter = "\\" + itemSuffix + "\\" + itemPrefix;

  static final String labelHealth = "HP: ";
  static final String labelSpeed = "Speed: ";
  static final String labelHeight = "Height: ";
  static final String labelArmor = itemPrefix;
  static final String labelStrength = "Strength: ";
  static final String labelOwner = "Owner: ";

  // 装備品
  static final EnumSet<Material> equipmentEnumSet = EnumSet.of(
      // サドル
      Material.SADDLE,
      // チェスト
      Material.CHEST,
      // ウマ鎧
      Material.LEATHER_HORSE_ARMOR, Material.IRON_HORSE_ARMOR, Material.GOLDEN_HORSE_ARMOR,
      Material.DIAMOND_HORSE_ARMOR,
      // カーペット
      Material.BLACK_CARPET, Material.BLUE_CARPET, Material.BROWN_CARPET, Material.CYAN_CARPET,
      Material.GRAY_CARPET, Material.GREEN_CARPET, Material.LIGHT_BLUE_CARPET,
      Material.LIGHT_GRAY_CARPET, Material.LIME_CARPET, Material.MAGENTA_CARPET,
      Material.MOSS_CARPET, Material.ORANGE_CARPET, Material.PINK_CARPET, Material.PURPLE_CARPET,
      Material.RED_CARPET, Material.WHITE_CARPET, Material.YELLOW_CARPET);

  static final List<String> eqipmentList = equipmentEnumSet.stream().map(e -> {
    return itemPrefix + e.name() + itemSuffix;
  }).collect(Collectors.toList());

  // ウマのカラー
  static final List<String> horseColorList = Arrays.asList(Horse.Color.values()).stream().map(e -> {
    return e.name();
  }).collect(Collectors.toList());

  // ラマのカラー
  static final List<String> llamaColorList = Arrays.asList(Llama.Color.values()).stream().map(e -> {
    return e.name();
  }).collect(Collectors.toList());

  // ウマのスタイル
  static final List<String> horseStyleList = Arrays.asList(Horse.Style.values()).stream().map(e -> {
    return e.name();
  }).collect(Collectors.toList());

  // ウマ卵テストデータ
  static final Map<String, Object> horseEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, false);
      put(dataKeySpeed, 1d);
      put(dataKeyHealth, 30d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyColor, Horse.Color.CHESTNUT.name());
      put(dataKeyJump, 1d);
      put(dataKeyMaxHealth, 30d);
      put(dataKeyName, "馬刺し");
      put(dataKeySaddle, true);
      put(dataKeyVariant, Horse.Variant.HORSE.name());
      put(dataKeyType, EntityType.HORSE.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyArmor, Material.DIAMOND_HORSE_ARMOR.name());
      put(dataKeyStyle, Horse.Style.WHITEFIELD.name());
      put(dataKeyStrength, null);
      put(dataKeyOwner, "TestOwner1");
    }
  };

  // ロバ卵テストデータ
  static final Map<String, Object> donkeyEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, true);
      put(dataKeySpeed, 0.17499999701976776d);
      put(dataKeyHealth, 23d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyJump, 0.5d);
      put(dataKeyMaxHealth, 23d);
      put(dataKeySaddle, true);
      put(dataKeyVariant, Horse.Variant.DONKEY.name());
      put(dataKeyType, EntityType.DONKEY.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyStrength, null);
      put(dataKeyOwner, null);
    }
  };

  // ラマ卵テストデータ
  static final Map<String, Object> llamaEggDataMap = new HashMap<>() {
    {
      put(dataKeyChest, true);
      put(dataKeySpeed, 0.17499999701976776d);
      put(dataKeyHealth, 21d);
      put(dataKeyUuidLeast, -6995725480010122770L);
      put(dataKeyColor, Llama.Color.GRAY.name());
      put(dataKeyJump, 0.5d);
      put(dataKeyMaxHealth, 21d);
      put(dataKeySaddle, false);
      put(dataKeyVariant, Horse.Variant.LLAMA.name());
      put(dataKeyType, EntityType.LLAMA.name());
      put(dataKeyUuidMost, 2968001111801612278L);
      put(dataKeyArmor, Material.PURPLE_CARPET.name());
      put(dataKeyStrength, 3);
      put(dataKeyOwner, "TestOwner2");
    }
  };

  @Mock
  EggDataBase eggData;

  @Before
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public final void testGetLoreList() {
    Mockito.doReturn(horseEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(!horseLw.getLoreList().isEmpty());
  }

  @Test
  public final void testGetColorStyleLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(horseEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validationColorStyle(horseLw.getColorStyleLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validationColorStyle(donkeyLw.getColorStyleLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validationColorStyle(llamaLw.getColorStyleLore()));
  }

  static boolean validationColorStyle(String lore) {
    if (StringUtils.isNotBlank(lore)) {
      // カラー、スタイルが設定されているか検査
      if (lore.matches("[A-Z/]+")) {
        List<String> colorOrStyleList = new ArrayList<>();

        if (lore.contains(valueSplitter)) {
          colorOrStyleList.addAll(Arrays.asList(lore.split(valueSplitter)));
        } else {
          colorOrStyleList.add(lore);
        }

        // カラー、スタイルの形式か検査
        // 設定された値が2つ以内か検査
        if (!(colorOrStyleList.size() <= 2)) {
          return false;
        }

        for (String colorOrStyleValue : colorOrStyleList) {
          // 文字列が大文字英字の表記であるか検査
          if (!colorOrStyleValue.matches("[A-Z]+")) {
            return false;
          }

          // 値がカラーまたは、スタイルで定義されている値か検査
          if (!(horseColorList.contains(colorOrStyleValue)
              || llamaColorList.contains(colorOrStyleValue)
              || horseStyleList.contains(colorOrStyleValue))) {
            return false;
          }
        }
      }
    }

    return true;
  }

  @Test
  public final void testGetHealthLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(horseEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateHealthLore(horseLw.getHealthLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateHealthLore(donkeyLw.getHealthLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateHealthLore(llamaLw.getHealthLore()));
  }

  static boolean validateHealthLore(String lore) {
    // 設定値を検査
    if (StringUtils.isBlank(lore)) {
      return false;
    }
    // 体力が設定されているか検査
    if (lore.startsWith(labelHealth)) {
      // [現在体力]/[最大体力]の形式か検査
      String healthNum = lore.substring(labelHealth.length());
      String[] healths = healthNum.split(valueSplitter);

      if (healths.length != 2) {
        return false;
      }

      // 値が有効な数値であるか検査
      for (String healthValue : healths) {
        // 文字列が数字の表記であるか検査
        if (!healthValue.matches("[0-9.]+")) {
          return false;
        }

        // 数字が有効な実数値か検査
        try {
          Double health = Double.valueOf(healthValue);

          if (health.isNaN()) {
            return false;
          }
        } catch (NumberFormatException nfe) {
          return false;
        }
      }
    } else {
      return false;
    }

    return true;
  }

  @Test
  public final void testGetHeightLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateHeightLore(horseLw.getHeightLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateHeightLore(donkeyLw.getHeightLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateHeightLore(llamaLw.getHeightLore()));
  }

  static boolean validateHeightLore(String lore) {
    // 設定値を検査
    if (StringUtils.isBlank(lore)) {
      return false;
    }

    // 跳躍力が設定されているか検査
    if (lore.startsWith(labelHeight)) {
      // 跳躍力の形式か検査
      String heightNum = lore.substring(labelHeight.length());

      // 文字列が5文字であるか検査
      if (heightNum.length() > 5) {
        return false;
      }

      // 文字列が数字の表記であるか検査
      if (!heightNum.matches("[0-9.]+")) {
        return false;
      }

      // 数字が有効な実数値か検査
      try {
        Double height = Double.valueOf(heightNum);

        if (height.isNaN()) {
          return false;
        }
      } catch (NumberFormatException nfe) {
        return false;
      }
    } else {
      return false;
    }

    return true;
  }

  @Test
  public final void testGetOwnerLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateOwnerLore(horseLw.getOwnerLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateOwnerLore(donkeyLw.getOwnerLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateOwnerLore(llamaLw.getOwnerLore()));
  }

  static boolean validateOwnerLore(String lore) {
    // 設定値を検査
    if (StringUtils.EMPTY.equals(lore)) {
      return false;
    }

    // オーナーが設定されているか検査
    if (StringUtils.isNotBlank(lore) && lore.startsWith(labelOwner)) {
      // オーナーの文字列が設定されているか検査
      String owner = lore.substring(labelOwner.length());
      if (StringUtils.isBlank(owner)) {
        return false;
      }
    }

    return true;
  }

  @Test
  public final void testGetEquipmentLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(horseEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(horseEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateEquipmentLore(horseLw.getEquipmentLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateEquipmentLore(donkeyLw.getEquipmentLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(llamaEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateEquipmentLore(llamaLw.getEquipmentLore()));
  }

  static boolean validateEquipmentLore(String lore) {
    // 設定値を検査
    if (StringUtils.isBlank(lore)) {
      return false;
    }

    // 装備が設定されているか検査
    if (lore.startsWith(labelArmor)) {
      // 装備の形式か検査
      List<String> equipList = new ArrayList<>();
      if (lore.contains("][")) {
        String[] equips = lore.split(itemSplitter);

        if (equips.length > 2) {
          return false;
        }

        equips[0] = equips[0] + itemSuffix;
        equips[1] = itemPrefix + equips[1];
        equipList.addAll(Arrays.asList(equips));

      } else {
        equipList.add(lore);
      }

      for (String equip : equipList) {
        if (!eqipmentList.contains(equip)) {
          return false;
        }
      }
    } else {
      return false;
    }

    return true;
  }

  @Test
  public final void testGetSpeedLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateSpeedLore(horseLw.getSpeedLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateSpeedLore(donkeyLw.getSpeedLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateSpeedLore(llamaLw.getSpeedLore()));
  }

  static boolean validateSpeedLore(String lore) {
    // 設定値を検査
    if (StringUtils.isBlank(lore)) {
      return false;
    }

    boolean isInvalid = false;
    // 移動速度が設定されているか検査
    if (lore.startsWith(labelSpeed)) {
      // 移動速度の形式か検査
      String jumpNum = lore.substring(labelSpeed.length());

      // 文字列が6文字であるか検査
      isInvalid = jumpNum.length() > 6;
      if (isInvalid) {
        return false;
      }

      // 文字列が数字の表記であるか検査
      isInvalid = !jumpNum.matches("[0-9.]+");
      if (isInvalid) {
        return false;
      }

      // 数字が有効な実数値か検査
      try {
        Double jump = Double.valueOf(jumpNum);

        if (jump.isNaN()) {
          return false;
        }
      } catch (NumberFormatException nfe) {
        return false;
      }
    } else {
      return false;
    }

    return true;
  }

  @Test
  public final void testGetStrengthLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    Assert.assertTrue(validateStrengthLore(horseLw.getStrengthLore()));

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    Assert.assertTrue(validateStrengthLore(donkeyLw.getStrengthLore()));

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    Assert.assertTrue(validateStrengthLore(llamaLw.getStrengthLore()));
  }

  static boolean validateStrengthLore(String lore) {
    // 設定値を検査
    if (StringUtils.EMPTY.equals(lore)) {
      return false;
    }

    boolean isInvalid = false;
    // 移動速度が設定されているか検査
    if (StringUtils.isNotBlank(lore) && lore.startsWith(labelStrength)) {
      // 移動速度の形式か検査
      String strengthNum = lore.substring(labelStrength.length());

      // 文字列が6文字であるか検査
      isInvalid = strengthNum.length() != 1;
      if (isInvalid) {
        return false;
      }

      // 文字列が有効な数字の表記であるか検査
      isInvalid = !strengthNum.matches("[1-5]");
      if (isInvalid) {
        return false;
      }
    }

    return true;
  }
}
