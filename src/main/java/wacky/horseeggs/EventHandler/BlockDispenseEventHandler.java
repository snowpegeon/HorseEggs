package wacky.horseeggs.EventHandler;

import com.github.teruteru128.logger.Logger;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import wacky.horseeggs.HorseEggs;

public class BlockDispenseEventHandler implements Listener {
  /**
   * ログのプレフィックス(TRACE).
   */
  private static final String PREF_LOG_TRACE = "[TRACE] ";
  /**
   * ログのプレフィックス(DEBUG).
   */
  private static final String PREF_LOG_DEBUG = "[DEBUG] ";
  /**
   * ログの開始.
   */
  private static final String PREF_LOG_START = "[START] ";
  /**
   * ログの終了.
   */
  private static final String PREF_LOG_END = "[END] ";

  /**
   * 内部ロガー
   */
  private Logger log;
  /**
   * プラグイン本体
   */
  private Plugin plugin;
  /**
   * プラグイン本体
   */
  private HorseEggs horseEggs;

  /**
   * ブロック排出イベント　コンストラクタ.
   *
   * @param plg       プラグイン本体.
   * @param horseEggs HorseEggに関するメソッドが入ってるクラス.
   */
  public BlockDispenseEventHandler(Plugin plg, HorseEggs horseEggs) {
    this.plugin = plg;
    this.log = Logger.getInstance(plugin);
    this.horseEggs = horseEggs;
  }

  /**
   * HorseEggがブロックから放出されたときのイベント処理.
   *
   * @param event ブロック放出イベント {@link org.bukkit.event.block.BlockDispenseEvent}
   */
  @EventHandler
  public void onBlockDispense(org.bukkit.event.block.BlockDispenseEvent event) {
    this.log.debug(
        PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.void:onBlockDispense(BlockDispenseEvent)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]event=" + event.toString());

    this.log.trace(PREF_LOG_TRACE + "event.isCancelled() ? " + event.isCancelled());
    this.log.trace(PREF_LOG_TRACE + "event.getBlock().getType() == Material.DROPPER ? "
        + (event.getBlock().getType() == Material.DROPPER));
    if (event.isCancelled() || event.getBlock().getType() == Material.DROPPER) {
      this.log.debug(
          PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.void:onBlockDispense(BlockDispenseEvent)");
      return;
    }

    this.log.trace(
        PREF_LOG_TRACE + "isHorseEgg(event.getItem()) ? " + horseEggs.isHorseEgg(event.getItem()));
    this.log.trace(
        PREF_LOG_TRACE + "isEmptyHorseEgg(event.getItem()) ? " + horseEggs.isEmptyHorseEgg(
            event.getItem()));
    if (horseEggs.isHorseEgg(event.getItem()) || horseEggs.isEmptyHorseEgg(event.getItem())) {
      this.log.debug(PREF_LOG_DEBUG + "event.getItem() is HorseEgg and Empty");
      event.setCancelled(true); // 仕様変更用にキャンセルだけ.凍結中
      /*
       * Dispenser dispenserM = (Dispenser) event.getBlock().getState().getData(); Location loc =
       * event.getBlock().getRelative(dispenserM.getFacing()).getLocation(); loc.add(0.5, 0.2, 0.5);
       * releaseHorse(event.getItem(),loc); org.bukkit.block.Dispenser dispenserS =
       * (org.bukkit.block.Dispenser)event.getBlock().getState();
       * dispenserS.getInventory().remove(event.getItem());
       */
    }
    this.log.debug(
        PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.void:onBlockDispense(BlockDispenseEvent)");
  }

}
