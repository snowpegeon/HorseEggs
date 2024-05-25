/**
 * Egg data of trader llama.
 */

package wacky.horseeggs.eggData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Objects;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.TraderLlama;
import org.bukkit.inventory.LlamaInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link TraderLlamaEggData} クラスを検証するテストクラスです.
 */
public class TraderLlamaEggDataTest {
  @Mock
  TraderLlama traderLlama;

  @Mock
  AbstractHorse absHorse;
  
  @Mock
  LlamaInventory llamaInv;
  
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

  private void setUpTraderLlama() {
    absHorse = traderLlama;
    when(traderLlama.getColor()).thenReturn(Llama.Color.WHITE);

    when(absHorse.getInventory()).thenReturn(llamaInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.LLAMA);
    when(absHorse.getType()).thenReturn(EntityType.TRADER_LLAMA);

  }

  /**
   * {@link TraderLlamaEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpTraderLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.TRADER_LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link TraderLlamaEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpTraderLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.TRADER_LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link TraderLlamaEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpTraderLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.TRADER_LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link TraderLlamaEggData#TraderLlamaEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testTraderLlamaEggData() {
    this.setUpTraderLlama();

    EggDataBase eggData = new TraderLlamaEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }

}
