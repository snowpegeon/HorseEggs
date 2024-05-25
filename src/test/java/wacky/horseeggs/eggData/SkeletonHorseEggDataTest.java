/**
 * Egg data of skeleton horse.
 */

package wacky.horseeggs.eggData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link SkeletonHorseEggData} クラスを検証するテストクラスです.
 */
public class SkeletonHorseEggDataTest {
  @Mock
  SkeletonHorse skeletonHorse;

  @Mock
  AbstractHorse absHorse;

  @Mock
  AbstractHorseInventory absHorseInv;
  
  @Mock
  AttributeInstance attr;
  
  @Mock
  AnimalTamer animalTamer;

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

  private void setUpSkeletonHorse() {
    absHorse = skeletonHorse;


    when(animalTamer.getName()).thenReturn("ホネの飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.SKELETON_HORSE);
    when(absHorse.getType()).thenReturn(EntityType.SKELETON_HORSE);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("うまいホネ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

  }

  /**
   * {@link SkeletonHorseEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpSkeletonHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.SKELETON_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link SkeletonHorseEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpSkeletonHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.SKELETON_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link SkeletonHorseEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpSkeletonHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.SKELETON_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link SkeletonHorseEggData#SkeletonHorseEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testHorseEggData() {
    this.setUpSkeletonHorse();

    EggDataBase eggData = new SkeletonHorseEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }
}
