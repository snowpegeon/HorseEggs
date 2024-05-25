/**
 * Egg data of llama.
 */

package wacky.horseeggs.eggData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Objects;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.LlamaInventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link LlamaEggData} クラスを検証するテストクラスです.
 */
public class LlamaEggDataTest {
  @Mock
  Llama llama;

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

  private void setUpLlama() {
    absHorse = llama;
    when(llama.getColor()).thenReturn(Llama.Color.WHITE);

    when(absHorse.getInventory()).thenReturn(llamaInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.LLAMA);
    when(absHorse.getType()).thenReturn(EntityType.LLAMA);

  }

  /**
   * {@link LlamaEggData#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link LlamaEggData#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link LlamaEggData#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link LlamaEggData#LlamaEggData()} のためのテスト・メソッド.
   */
  @Test
  public final void testLlamaEggData() {
    this.setUpLlama();

    EggDataBase eggData = new LlamaEggData();
    Assert.assertTrue(Objects.nonNull(eggData));
  }

}
