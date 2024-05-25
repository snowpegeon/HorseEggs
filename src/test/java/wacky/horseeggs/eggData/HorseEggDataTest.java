/**
 * Egg data of horse.
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
import org.bukkit.inventory.HorseInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link HorseEggData} クラスを検証するテストクラスです.
 */
public class HorseEggDataTest {
  @Mock
  Horse horse;

  @Mock
  AbstractHorse absHorse;
  
  @Mock
  HorseInventory horseInv;
  
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

  private void setUpHorse() {
    absHorse = horse;
    when(horse.getColor()).thenReturn(Horse.Color.BLACK);
    when(horse.getStyle()).thenReturn(Horse.Style.NONE);

    when(animalTamer.getName()).thenReturn("ウマの飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(horseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.HORSE);
    when(absHorse.getType()).thenReturn(EntityType.HORSE);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("うまいウマ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

  }

  /**
   * {@link HorseEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link HorseEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link HorseEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link HorseEggData#HorseEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testHorseEggData() {
    this.setUpHorse();

    EggDataBase eggData = new HorseEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }

}
