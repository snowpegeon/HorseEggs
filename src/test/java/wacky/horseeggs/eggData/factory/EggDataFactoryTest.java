/**
 * Factory for horse egg data. 
 */

package wacky.horseeggs.eggData.factory;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * {@link EggDataFactory} クラスを検証するテストクラスです.
 */
public class EggDataFactoryTest {
  @Mock
  AbstractHorse absHorse;

  @Mock
  Material eggType;

  @Mock
  HashMap<String, ?> metaData;

  /**
   * 事前準備.
   *
   * @throws java.lang.Exception すべての例外
   */
  @Before
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * {@link EggDataFactory#newEggData(EntityType, AbstractHorse)} のためのテスト・メソッド.
   */
  @Test
  public final void testNewEggDataEntityTypeAbstractHorse() {
    // ラマ
    Mockito.doReturn(Horse.Variant.LLAMA).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.LLAMA).when(absHorse).getType();
    var inventory = mock(AbstractHorseInventory.class);
    Mockito.doReturn(mock(ItemStack.class)).when(inventory).getSaddle();
    Mockito.doReturn(inventory).when(absHorse).getInventory();
    EggDataBase llamaEd = EggDataFactory.newEggData(EntityType.LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(llamaEd));

    // ラバ
    Mockito.doReturn(Horse.Variant.MULE).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.MULE).when(absHorse).getType();
    EggDataBase muleEd = EggDataFactory.newEggData(EntityType.MULE, absHorse);
    Assert.assertTrue(Objects.nonNull(muleEd));

    // ロバ
    Mockito.doReturn(Horse.Variant.DONKEY).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.DONKEY).when(absHorse).getType();
    EggDataBase donkeyEd = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    Assert.assertTrue(Objects.nonNull(donkeyEd));

    // ウマ
    Mockito.doReturn(Horse.Variant.HORSE).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.HORSE).when(absHorse).getType();
    EggDataBase horseEd = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(horseEd));

    // ゾンビホース
    Mockito.doReturn(Horse.Variant.UNDEAD_HORSE).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.ZOMBIE_HORSE).when(absHorse).getType();
    EggDataBase zombieHorseEd = EggDataFactory.newEggData(EntityType.ZOMBIE_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(zombieHorseEd));

    // スケルトンホース
    Mockito.doReturn(Horse.Variant.SKELETON_HORSE).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.SKELETON_HORSE).when(absHorse).getType();
    EggDataBase skeltonHorseEd = EggDataFactory.newEggData(EntityType.SKELETON_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(skeltonHorseEd));

    // 行商人のラマ
    Mockito.doReturn(Horse.Variant.LLAMA).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.LLAMA).when(absHorse).getType();
    EggDataBase traderLlamaEd = EggDataFactory.newEggData(EntityType.TRADER_LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(traderLlamaEd));

    // その他（ラクダ）
    Mockito.doReturn(Horse.Variant.CAMEL).when(absHorse).getVariant();
    Mockito.doReturn(EntityType.CAMEL).when(absHorse).getType();
    EggDataBase otherEd = EggDataFactory.newEggData(EntityType.CAMEL, absHorse);
    Assert.assertTrue(Objects.isNull(otherEd));
  }

  /**
   * {@link EggDataFactory#newEggData(Material, HashMap)} のためのテスト・メソッド.
   */
  @Test
  public final void testNewEggDataMaterialHashMapOfStringQ() {
    // ラマ
    EggDataBase llamaEd = EggDataFactory.newEggData(Material.LLAMA_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(llamaEd));

    // ラバ
    EggDataBase muleEd = EggDataFactory.newEggData(Material.MULE_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(muleEd));

    // ロバ
    EggDataBase donkeyEd = EggDataFactory.newEggData(Material.DONKEY_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(donkeyEd));

    // ウマ
    EggDataBase horseEd = EggDataFactory.newEggData(Material.HORSE_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(horseEd));

    // ゾンビホース
    EggDataBase zombieHorseEd =
        EggDataFactory.newEggData(Material.ZOMBIE_HORSE_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(zombieHorseEd));

    // スケルトンホース
    EggDataBase skeltonHorseEd =
        EggDataFactory.newEggData(Material.SKELETON_HORSE_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(skeltonHorseEd));

    // 行商人のラマ
    EggDataBase traderLlamaEd =
        EggDataFactory.newEggData(Material.TRADER_LLAMA_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(traderLlamaEd));

    // その他（ラクダ）
    EggDataBase otherEd = EggDataFactory.newEggData(Material.CAMEL_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.isNull(otherEd));
  }

  /**
   * {@link EggDataFactory#isCaptureAbleEggMaterial(Material)} のためのテスト・メソッド.
   */
  @Test
  public final void testIsCaptureAbleEggMaterial() {
    // 捕獲可能
    boolean isCapturable = EggDataFactory.isCaptureAbleEggMaterial(Material.HORSE_SPAWN_EGG);
    Assert.assertTrue(isCapturable);

    // 捕獲不可
    boolean isNotCapturable = EggDataFactory.isCaptureAbleEggMaterial(Material.CAMEL_SPAWN_EGG);
    Assert.assertFalse(isNotCapturable);
  }

}
