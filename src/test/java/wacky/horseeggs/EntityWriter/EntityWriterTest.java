/**
 * 
 */
package wacky.horseeggs.EntityWriter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.AbstractHorseInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * 
 */
//@RunWith(org.bukkit.Bukkit.class)
public class EntityWriterTest {
  
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
  
  @Mock
  AbstractHorse absHorse;
  
  @Mock
  Logger logger;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * {@link EntityWriter#EntityWriter(AbstractHorse)} のためのテスト・メソッド。
   */
  @Ignore("EntityWriterTest#testWriteHorseBase で検証")
  @Test
  public final void testEntityWriter() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link EntityWriter#writeHorse(EggDataBase)} のためのテスト・メソッド。
   */
  @Ignore("継承先クラスで検証")
  @Test
  public final void testWriteHorse() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link EntityWriter#writeHorseBase(EggDataBase)} のためのテスト・メソッド。
   */
  @Test
  public final void testWriteHorseBase() {
    // ウマ
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Mockito.doReturn(horseEggDataMap.get(dataKeyName)).when(eggData).getName();
      Mockito.doReturn(horseEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
      Mockito.doReturn(horseEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
      Mockito.doReturn(horseEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
      Mockito.doReturn(horseEggDataMap.get(dataKeyJump)).when(eggData).getJump();
      Mockito.doReturn(horseEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
      Mockito.doReturn(horseEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
      Mockito.doReturn(horseEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
      Mockito.doReturn(horseEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
      Mockito.doReturn(EntityType.HORSE).when(eggData).getEntityType();

      AttributeInstance attr = mock(AttributeInstance.class);
      Mockito.doReturn(attr).when(absHorse).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

      Long uuidMost = Long.valueOf(horseEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(horseEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      OfflinePlayer offlinePlayer = mock(OfflinePlayer.class);
      Server server = mock(Server.class);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);
      AbstractHorseInventory absHorseInv = mock(AbstractHorseInventory.class);
      when(absHorse.getInventory()).thenReturn(absHorseInv);

      EntityWriter horseEw = EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse, logger);
      Assert.assertTrue(horseEw.writeHorseBase(eggData));
//      AbstractHorse horseAbsHorse = horseEw.getAbsHorse();
//      Assert.assertTrue(Objects.nonNull(horseAbsHorse));
    }

    // ロバ
    absHorse = mock(Donkey.class);
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyName)).when(eggData).getName();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyJump)).when(eggData).getJump();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
      Mockito.doReturn(donkeyEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
      Mockito.doReturn(EntityType.DONKEY).when(eggData).getEntityType();

      AttributeInstance attr = mock(AttributeInstance.class);
      Mockito.doReturn(attr).when(absHorse).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

      Long uuidMost = Long.valueOf(donkeyEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(donkeyEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      OfflinePlayer offlinePlayer = mock(OfflinePlayer.class);
      Server server = mock(Server.class);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);

      AbstractHorseInventory absHorseInv = mock(AbstractHorseInventory.class);
      when(absHorse.getInventory()).thenReturn(absHorseInv);


      EntityWriter donkeyEw =
          EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse, logger);
      Assert.assertTrue(donkeyEw.writeHorseBase(eggData));
      AbstractHorse donkeyAbsHorse = donkeyEw.getAbsHorse();
      Assert.assertTrue(Objects.nonNull(donkeyAbsHorse));
    }

    // ラマ
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      Mockito.doReturn(llamaEggDataMap.get(dataKeyName)).when(eggData).getName();
      Mockito.doReturn(llamaEggDataMap.get(dataKeyMaxHealth)).when(eggData).getMaxHealth();
      Mockito.doReturn(llamaEggDataMap.get(dataKeyHealth)).when(eggData).getHealth();
      Mockito.doReturn(llamaEggDataMap.get(dataKeySpeed)).when(eggData).getSpeed();
      Mockito.doReturn(llamaEggDataMap.get(dataKeyJump)).when(eggData).getJump();
      // Mockito.doReturn(llamaEggDataMap.get(dataKeyUuidMost)).when(eggData).getUuidMost();
      Mockito.doReturn(null).when(eggData).getUuidMost();
      // Mockito.doReturn(llamaEggDataMap.get(dataKeyUuidLeast)).when(eggData).getUuidLeast();
      Mockito.doReturn(null).when(eggData).getUuidLeast();
      Mockito.doReturn(llamaEggDataMap.get(dataKeySaddle)).when(eggData).getIsSaddled();
      Mockito.doReturn(llamaEggDataMap.get(dataKeyChest)).when(eggData).getIsCarryingChest();
      Mockito.doReturn(EntityType.LLAMA).when(eggData).getEntityType();

      AttributeInstance attr = mock(AttributeInstance.class);
      Mockito.doReturn(attr).when(absHorse).getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);

      Long uuidMost = Long.valueOf(llamaEggDataMap.get(dataKeyUuidMost).toString());
      Long uuidLeast = Long.valueOf(llamaEggDataMap.get(dataKeyUuidLeast).toString());
      UUID id = new UUID(uuidMost, uuidLeast);

      OfflinePlayer offlinePlayer = mock(OfflinePlayer.class);
      Server server = mock(Server.class);

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();

      when(server.getOfflinePlayer(id)).thenReturn(offlinePlayer);

      EntityWriter llamaEw = EntityWriterFactory.newEntityWriter(eggData.getEntityType(), absHorse, logger);
      Assert.assertTrue(llamaEw.writeHorseBase(eggData));
      
      AbstractHorse llamaAbsHorse = llamaEw.getAbsHorse();
      Assert.assertTrue(Objects.nonNull(llamaAbsHorse));
    }
  }

  /**
   * {@link wacky.horseeggs.EntityWriter.EntityWriter#getAbsHorse()} のためのテスト・メソッド。
   */
  @Ignore("EntityWriterTest#testWriteHorseBase で検証")
  @Test
  public final void testGetAbsHorse() {
    fail("まだ実装されていません"); // TODO
  }
}
