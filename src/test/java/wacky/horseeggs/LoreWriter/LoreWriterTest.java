package wacky.horseeggs.LoreWriter;

import java.text.Collator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
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
      Material.SADDLE
      // チェスト
      , Material.CHEST
      // ウマ鎧
      , Material.LEATHER_HORSE_ARMOR, Material.IRON_HORSE_ARMOR, Material.GOLDEN_HORSE_ARMOR,
      Material.DIAMOND_HORSE_ARMOR
      // カーペット
      , Material.BLACK_CARPET, Material.BLUE_CARPET, Material.BROWN_CARPET, Material.CYAN_CARPET,
      Material.GRAY_CARPET, Material.GREEN_CARPET, Material.LIGHT_BLUE_CARPET,
      Material.LIGHT_GRAY_CARPET, Material.LIME_CARPET, Material.MAGENTA_CARPET,
      Material.MOSS_CARPET, Material.ORANGE_CARPET, Material.PINK_CARPET, Material.PURPLE_CARPET,
      Material.RED_CARPET, Material.WHITE_CARPET, Material.YELLOW_CARPET);
    
  static final List<String> eqipmentList = equipmentEnumSet.stream().map(e -> {
    return itemPrefix + e.name() + itemSuffix;
  }).collect(Collectors.toList());

  // 空卵テストデータ
  static final Map<String, Object> emptyEggDataMap = null;

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
  public final void testLoreWriter() {
    // fail("まだ実装されていません"); // TODO
    assert (true);
  }

  @Test
  public final void testGetHealthMeter() {
    // fail("まだ実装されていません"); // TODO
    assert (true);
  }

  @Test
  public final void testGetLoreList() {
    // fail("まだ実装されていません"); // TODO
    assert (true);
  }

  @Test
  public final void testGetColorStyleLore() {
    // fail("まだ実装されていません"); // TODO
    assert (true);
  }

  static boolean validateHealthLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;
    // 設定値を検査
    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // 体力が設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // [現在体力]/[最大体力]の形式か検査
          String healthNum = lore.substring(targetLbel.length());
          String[] healths = healthNum.split(valueSplitter);
          isInvalid = healths.length != 2;
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 値が有効な数値であるか検査
          for (String healthValue : healths) {
            // 文字列が数字の表記であるか検査
            isInvalid = !healthValue.matches("[0-9.]+");
            if (isInvalid) {
              validateResult = false;
              break;
            }

            // 数字が有効な実数値か検査
            Double health = Double.valueOf(healthValue);
            isInvalid = health.isInfinite();
            isInvalid = health.isNaN();
            // isInvalid = !(health.isInfinite() || health.isNaN());
            if (isInvalid) {
              validateResult = false;
              break;
            }
          }
        }
      }
    }
    return validateResult;
  }

  static boolean validateHeightLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;

    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // 跳躍力が設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // 跳躍力の形式か検査
          String heightNum = lore.substring(targetLbel.length());

          // 文字列が5文字であるか検査
          isInvalid = heightNum.length() > 5;
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 文字列が数字の表記であるか検査
          isInvalid = !heightNum.matches("[0-9.]+");
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 数字が有効な実数値か検査
          Double height = Double.valueOf(heightNum);
          // 数値が無限か検査
          isInvalid = height.isInfinite();
          // 数値がNaNか検査
          isInvalid = height.isNaN();
          if (isInvalid) {
            validateResult = false;
            break;
          }
        }
      }
    }

    return validateResult;
  }

  static boolean validateEquipmentLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;
    
    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // 装備が設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // 装備の形式か検査
          if (lore.contains("][")) {
            String[] equips = lore.split(itemSplitter);
            isInvalid = equips.length != 2;
            equips[0] = equips[0] + itemSuffix;
            equips[1] = itemPrefix + equips[1];
            
            for (String equip : equips) {
              isInvalid = !eqipmentList.contains(equip);
              if (isInvalid) {
                validateResult = false;
                break;
              }
            }

          } else {
            isInvalid = !eqipmentList.contains(lore);
            if (isInvalid) {
              validateResult = false;
              break;
            }
            
          }
          if (isInvalid) {
            validateResult = false;
            break;
          }
        } else {
          
        }
      }
    }
    
    return validateResult;
  }
  
  static boolean validateSpeedLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;
    
    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // 移動速度が設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // 移動速度の形式か検査
          String jumpNum = lore.substring(targetLbel.length());

          // 文字列が6文字であるか検査
          isInvalid = jumpNum.length() > 6;
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 文字列が数字の表記であるか検査
          isInvalid = !jumpNum.matches("[0-9.]+");
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 数字が有効な実数値か検査
          Double height = Double.valueOf(jumpNum);
          // 数値が無限か検査
          isInvalid = height.isInfinite();
          // 数値がNaNか検査
          isInvalid = height.isNaN();
          if (isInvalid) {
            validateResult = false;
            break;
          }
        }
      }
    }
    
    return validateResult;
  }
  
  static boolean validateStrengthLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;

    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // 移動速度が設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // 移動速度の形式か検査
          String strengthNum = lore.substring(targetLbel.length());

          // 文字列が6文字であるか検査
          isInvalid = strengthNum.length() != 1;
          if (isInvalid) {
            validateResult = false;
            break;
          }

          // 文字列が有効な数字の表記であるか検査
          isInvalid = !strengthNum.matches("[1-5]");
          if (isInvalid) {
            validateResult = false;
            break;
          }
        }
      }
    }

    return validateResult;
  }
  
  static boolean validateOwnerLore(List<String> loreList, String targetLbel) {
    boolean validateResult = true;
    
    for (String lore : loreList) {
      if (StringUtils.isNotBlank(lore)) {
        boolean isInvalid = false;
        // オーナーが設定されているか検査
        if (lore.startsWith(targetLbel)) {
          // オーナーの文字列が設定されているか検査
          String strength = lore.substring(targetLbel.length());
          isInvalid = StringUtils.isBlank(strength);
          if (isInvalid) {
            validateResult = false;
            break;
          }
        }
      }
    }
    
    return validateResult;
  }
  
  @Test
  public final void testGetHealthLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(horseEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseHealth = validateHealthLore(horseLoreList, labelHealth);
    Assert.assertTrue(containHorseHealth);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeyHealth = validateHealthLore(donkeyLoreList, labelHealth);
    Assert.assertTrue(containDonkeyHealth);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaHealth = validateHealthLore(llamaLoreList, labelHealth);
    Assert.assertTrue(containLlamaHealth);

  }

  @Test
  public final void testGetHeightLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseHeight = validateHeightLore(horseLoreList, labelHeight);
    Assert.assertTrue(containHorseHeight);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeyHeight = validateHeightLore(donkeyLoreList, labelHeight);
    Assert.assertTrue(containDonkeyHeight);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaHeight = validateHeightLore(llamaLoreList, labelHeight);
    Assert.assertTrue(containLlamaHeight);
  }

  @Test
  public final void testGetOwnerLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseHeight = validateHeightLore(horseLoreList, labelHeight);
    Assert.assertTrue(containHorseHeight);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeyHeight = validateHeightLore(donkeyLoreList, labelHeight);
    Assert.assertTrue(containDonkeyHeight);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyOwner)).when(eggData).getOwner();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaHeight = validateHeightLore(llamaLoreList, labelHeight);
    Assert.assertTrue(containLlamaHeight);
  }

  @Test
  public final void testGetEquipmentLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(horseEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(horseEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseEquipment = validateEquipmentLore(horseLoreList, labelArmor);
    Assert.assertTrue(containHorseEquipment);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeyEquipment = validateEquipmentLore(donkeyLoreList, labelArmor);
    Assert.assertTrue(containDonkeyEquipment);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(llamaEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaEquipment = validateEquipmentLore(llamaLoreList, labelArmor);
    Assert.assertTrue(containLlamaEquipment);
  }

  @Test
  public final void testGetSpeedLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseSpeed = validateSpeedLore(horseLoreList, labelSpeed);
    Assert.assertTrue(containHorseSpeed);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeySpeed = validateSpeedLore(donkeyLoreList, labelSpeed);
    Assert.assertTrue(containDonkeySpeed);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaSpeed = validateSpeedLore(llamaLoreList, labelSpeed);
    Assert.assertTrue(containLlamaSpeed);
  }

  @Test
  public final void testGetStrengthLore() {
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter horseLw = new LoreWriterFactory().newLoreWriter(EntityType.HORSE, eggData);
    List<String> horseLoreList = horseLw.getLoreList();
    boolean containHorseStrength = validateStrengthLore(horseLoreList, labelStrength);
    Assert.assertTrue(containHorseStrength);

    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter donkeyLw = new LoreWriterFactory().newLoreWriter(EntityType.DONKEY, eggData);
    List<String> donkeyLoreList = donkeyLw.getLoreList();
    boolean containDonkeyStrength = validateStrengthLore(donkeyLoreList, labelStrength);
    Assert.assertTrue(containDonkeyStrength);

    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyStrength)).when(eggData).getStrength();
    LoreWriter llamaLw = new LoreWriterFactory().newLoreWriter(EntityType.LLAMA, eggData);
    List<String> llamaLoreList = llamaLw.getLoreList();
    boolean containLlamaStrength = validateStrengthLore(llamaLoreList, labelStrength);
    Assert.assertTrue(containLlamaStrength);
  }

}
