/**
 * HorseEggs Plugin.
 */

package wacky.horseeggs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

/**
 * {@link HorseEggs} クラスを検証するテストクラスです.
 */
class HorseEggsTest {
  @Mock
  JavaPlugin plugin;
  
  @Mock
  Server server;
  
  @Mock
  ItemFactory itemFactory;

  @Mock
  ItemStack itemStack;
  
  @Mock
  ItemMeta itemMeta;
  
  /**
   * 事前準備.
   *
   * @throws java.lang.Exception すべての例外
   */
  @Before
  void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }
  
  /**
   * {@link HorseEggs#onDisable()} のためのテスト・メソッド.
   */
  @Ignore("実装なしのためテストなし")
  @Test
  final void testOnDisable() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link HorseEggs#onEnable()} のためのテスト・メソッド.
   */
  @Ignore
  @Test
  final void testOnEnable() {
//    fail("まだ実装されていません"); // TODO
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      
    }
  }

  /**
   * {@link HorseEggs#emptyHorseEgg(int)} のためのテスト・メソッド.
   */
  @Test
  final void testEmptyHorseEgg() {
//    fail("まだ実装されていません"); // TODO
//    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
//      when(itemStack.getItemMeta()).thenReturn(itemMeta);
//    }
//    ItemStack horseEgg = 
  }

  /**
   * {@link HorseEggs#isEmptyHorseEgg(ItemStack)} のためのテスト・メソッド.
   */
  @Ignore
  @Test
  final void testIsEmptyHorseEgg() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link HorseEggs#isHorseEgg(ItemStack)} のためのテスト・メソッド.
   */
  @Ignore
  @Test
  final void testIsHorseEgg() {
    fail("まだ実装されていません"); // TODO
  }

  /**
   * {@link HorseEggs#isClickable(Block)} のためのテスト・メソッド.
   */
  @Ignore
  @Test
  final void testIsClickable() {
    fail("まだ実装されていません"); // TODO
  }

}
