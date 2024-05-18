package wacky.horseeggs.LoreWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

  // 空卵データ
  static final Map<String, Object> emptyEggDataMap = null;

  // ウマ卵データ
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
    }
  };

  // ロバ卵データ
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

  // ラマ卵データ
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
    }
  };

  @Mock
  AbstractHorse absHorse;

  @Mock
  EggDataBase eggData;

  @Spy
  @InjectMocks
  LoreWriter loreWriter;

  @Before
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  // HorseEggData createHorseEggData() {
  // new Server.Spigot();
  // Bukkit.spigot();
  // Spigot spigot = new Spigot();
  // final EntityType entityTypeHorse = EntityType.HORSE;

  // ワールド生成
  // WorldCreator wc = new WorldCreator("test");
  // Server server; // = new Server();
  // Bukkit bukkit; // = new Bukkit();
  // World world = wc.createWorld();

  // 位置をセット
  // Location loc = new Location(world, 0d, 0d, 0d);

  // エンティティをスポーン
  // Entity entity = loc.getWorld().spawnEntity(loc, entityTypeHorse, false);
  // Horse horse = (Horse) entity;
  // Horse horse = new Horse()

  // ウマ卵データ作成
  // EggDataBase eggData = new EggDataFactory().newEggData(entityTypeHorse, horse);
  // return (HorseEggData) eggData;
  // }

  @Test
  public final void testLoreWriter() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetHealthMeter() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetLoreList() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetColorStyleLore() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetHealthLore() {
    // 入力ソースの振る舞いを設定
    // ウマ
    Mockito.doReturn(horseEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(horseEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    Mockito.doReturn(horseEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(horseEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
    Mockito.doReturn(horseEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(horseEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    Mockito.doReturn(horseEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    Mockito.doReturn(horseEggDataMap.get(dataKeyName)).when(eggData).getName();
    Mockito.doReturn(horseEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    Mockito.doReturn(horseEggDataMap.get(dataKeyVariant)).when(eggData).getVariant();
    Mockito.doReturn(horseEggDataMap.get(dataKeyType)).when(eggData).getType();
    Mockito.doReturn(horseEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
    Mockito.doReturn(horseEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(horseEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    
    
    
    // ロバ
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyName)).when(eggData).getName();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyVariant)).when(eggData).getVariant();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyType)).when(eggData).getType();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(donkeyEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    
    // ラマ
    Mockito.doReturn(llamaEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
    Mockito.doReturn(llamaEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyColor)).when(eggData).getColor();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyJump)).when(eggData).getJump();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyName)).when(eggData).getName();
    Mockito.doReturn(llamaEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyVariant)).when(eggData).getVariant();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyType)).when(eggData).getType();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyArmor)).when(eggData).getArmor();
    Mockito.doReturn(llamaEggDataMap.get(dataKeyStyle)).when(eggData).getStyle();
    

  }

  @Test
  public final void testGetHeightLore() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetOwnerLore() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetEquipmentLore() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetSpeedLore() {
//    fail("まだ実装されていません"); // TODO
  }

  @Test
  public final void testGetStrengthLore() {
//    fail("まだ実装されていません"); // TODO
  }

}
