/**
 * 
 */
package wacky.horseeggs.LoreWriter.factory;

import static org.junit.Assert.*;
import java.util.Objects;
import org.bukkit.entity.EntityType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * 
 */
public class LoreWriterFactoryTest {
  @Mock
  EggDataBase eggData;

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
   * {@link wacky.horseeggs.LoreWriter.factory.LoreWriterFactory#newLoreWriter(org.bukkit.entity.EntityType, wacky.horseeggs.eggData.EggDataBase)} のためのテスト・メソッド。
   */
  @Test
  public final void testNewLoreWriter() {
    // ラマ
    LoreWriter llamaLw = LoreWriterFactory.newLoreWriter(EntityType.LLAMA, eggData);
    assertTrue(Objects.nonNull(llamaLw));

    // ラバ
    LoreWriter muleLw = LoreWriterFactory.newLoreWriter(EntityType.MULE, eggData);
    assertTrue(Objects.nonNull(muleLw));
    
    // ロバ
    LoreWriter donkeyLw = LoreWriterFactory.newLoreWriter(EntityType.DONKEY, eggData);
    assertTrue(Objects.nonNull(donkeyLw));
    
    // ウマ
    LoreWriter horseLw = LoreWriterFactory.newLoreWriter(EntityType.HORSE, eggData);
    assertTrue(Objects.nonNull(horseLw));
    
    // ゾンビホース
    LoreWriter zombieHorseLw = LoreWriterFactory.newLoreWriter(EntityType.ZOMBIE_HORSE, eggData);
    assertTrue(Objects.nonNull(zombieHorseLw));
    
    // スケルトンホース
    LoreWriter skeltonHorseLw = LoreWriterFactory.newLoreWriter(EntityType.SKELETON_HORSE, eggData);
    assertTrue(Objects.nonNull(skeltonHorseLw));
    
    // 行商人のラマ
    LoreWriter traderLammaLw = LoreWriterFactory.newLoreWriter(EntityType.TRADER_LLAMA, eggData);
    assertTrue(Objects.nonNull(traderLammaLw));
    
    // コウモリだけが知っている
    LoreWriter otherLw = LoreWriterFactory.newLoreWriter(EntityType.BAT, eggData);
    assertTrue(Objects.nonNull(otherLw));
  }

}
