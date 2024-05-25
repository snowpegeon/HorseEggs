/**
 * Egg data of mule.
 */

package wacky.horseeggs.eggData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Objects;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Mule;
import org.bukkit.inventory.AbstractHorseInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link MuleEggData} クラスを検証するテストクラスです.
 */
public class MuleEggDataTest {
  @Mock
  Mule mule;
  
  @Mock
  AbstractHorse absHorse;
  
  @Mock
  AbstractHorseInventory absHorseInv;

  @Mock
  ChestedHorse chestedHorse;

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

  private void setUpMule() {
    absHorse = mule;
    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.MULE);
    when(absHorse.getType()).thenReturn(EntityType.MULE);
    when(chestedHorse.isCarryingChest()).thenReturn(true);

  }

  /**
   * {@link MuleEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpMule();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.MULE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link MuleEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpMule();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.MULE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link MuleEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpMule();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.MULE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link MuleEggData#MuleEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testMuleEggData() {
    this.setUpMule();

    EggDataBase eggData = new MuleEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }

}
