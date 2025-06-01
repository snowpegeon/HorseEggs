/**
 * Egg data of zombie horse.
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
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.AbstractHorseInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link ZombieHorseEggData} クラスを検証するテストクラスです.
 */
public class ZombieHorseEggDataTest {
  @Mock
  ZombieHorse zombieHorse;
  
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

  private void setUpZombieHorse() {
    absHorse = zombieHorse;


    when(animalTamer.getName()).thenReturn("まずウマの飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.SKELETON_HORSE);
    when(absHorse.getType()).thenReturn(EntityType.ZOMBIE_HORSE);
    when(absHorse.getAttribute(Attribute.MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("まずいウマ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

  }

  /**
   * {@link ZombieHorseEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpZombieHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.ZOMBIE_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link ZombieHorseEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpZombieHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.ZOMBIE_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link ZombieHorseEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpZombieHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.ZOMBIE_HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link ZombieHorseEggData#ZombieHorseEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testZombieHorseEggData() {
    this.setUpZombieHorse();

    EggDataBase eggData = new ZombieHorseEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }

}
