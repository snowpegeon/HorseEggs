package wacky.horseeggs;

import com.github.teruteru128.logger.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.Container;
import org.bukkit.block.DaylightDetector;
import org.bukkit.block.Dispenser;
import org.bukkit.block.EnchantingTable;
import org.bukkit.block.EnderChest;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.block.Jukebox;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import wacky.horseeggs.minecraftIO.PlayerInteractListener;

/**
 * HorseEggs クラス.
 */
public class HorseEggs extends JavaPlugin implements Listener {
  /** ログのプレフィックス(TRACE). */
  private static final String PREF_LOG_TRACE = "[TRACE] ";
  /** ログのプレフィックス(DEBUG). */
  private static final String PREF_LOG_DEBUG = "[DEBUG] ";
  /** ログの開始. */
  private static final String PREF_LOG_START = "[START] ";
  /** ログの終了. */
  private static final String PREF_LOG_END = "[END] ";
  /** 設定ファイル. */
  public FileConfiguration config;
  /** ロガー. */
  private Logger log;

  /**
   * このプラグインが有効化される時の処理.
   */
  @Override
  public void onEnable() {
    // 設定ファイル読み込み
    this.config = this.getConfig();
    this.config.options().copyDefaults(true);
    // this.config.options().header("HorseEggs Configuration");
    this.config.options().setHeader(Arrays.asList("HorseEggs Configuration"));
    this.saveConfig();

    // ロガーの設定
    var logLevel = this.config.getString("log-level");
    Logger.register(this, logLevel);
    this.log = Logger.getInstance(this);
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.void:onEnable()");

    // レシピの登録
    NamespacedKey namespacedKey = new NamespacedKey(this, "horseEggs");
    this.log.trace(
        PREF_LOG_TRACE + "NamespacedKey namespacedKey <- new NamespacedKey(this, \"horseEggs\")");
    // ShapedRecipe storageSignRecipe = new ShapedRecipe(emptyHorseEgg(1));
    // this.logger.trace(
    // LOG_PREFIX_TRACE + "ShapedRecipe storageSignRecipe <- new ShapedRecipe(emptyHorseEgg(1))");
    ShapedRecipe horseEggsRecipe = new ShapedRecipe(namespacedKey, emptyHorseEgg(1));
    this.log.trace(PREF_LOG_TRACE
        + "ShapedRecipe storageSignRecipe <- new ShapedRecipe(namespacedKey, emptyHorseEgg(1))");

    final String shapeRow1 = " P ";
    final String shapeRow2 = "PEP";
    final String shapeRow3 = " P ";
    horseEggsRecipe.shape(shapeRow1, shapeRow2, shapeRow3);
    this.log.trace(PREF_LOG_TRACE + "storageSignRecipe.shape(" + shapeRow1 + "," + shapeRow2 + ","
        + shapeRow3 + ")");

    horseEggsRecipe.setIngredient('P', Material.ENDER_PEARL);
    this.log.trace(PREF_LOG_TRACE + "storageSignRecipe.setIngredient('P', Material.ENDER_PEARL)");

    horseEggsRecipe.setIngredient('E', Material.EGG);
    this.log.trace(PREF_LOG_TRACE + "storageSignRecipe.setIngredient('E', Material.EGG)");

    boolean wereAbleToAddRecipe = getServer().addRecipe(horseEggsRecipe);
    this.log.trace(
        PREF_LOG_TRACE + "boolean wereAbleToAddRecipe <- getServer().addRecipe(storageSignRecipe)");
    this.log.trace(PREF_LOG_TRACE + "wereAbleToAddRecipe=" + wereAbleToAddRecipe);

    // リスナーの登録
    // TODO ちゃんと個別にリスナーを登録したほうがいい
    getServer().getPluginManager().registerEvents(this, this);
    this.log.trace(PREF_LOG_TRACE + "getServer().getPluginManager().registerEvents(this, this)");

    // リスナーの開始
    new PlayerInteractListener(this, log);
    this.log.trace(PREF_LOG_TRACE + "new PlayerInteractListener(this, logger)");

    // new ItemDespawnListener(this);
    this.log.info("HorseEggs plugin enable done.");
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.void:onEnable()");
  }

  /**
   * このプラグインが無効化される時の処理.
   */
  @Override
  public void onDisable() {
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.voidonDisable()");
    // Nothing to do.
    this.log.info("HorseEggs plugin diable done.");
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.void:onDisable()");
  }

  /**
   * HorseEggがブロックから放出されたときのイベント処理.
   *
   * @param event ブロック放出イベント {@link org.bukkit.event.block.BlockDispenseEvent}
   */
  @EventHandler
  public void onBlockDispense(BlockDispenseEvent event) {
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

    this.log.trace(PREF_LOG_TRACE + "isHorseEgg(event.getItem()) ? " + isHorseEgg(event.getItem()));
    this.log.trace(
        PREF_LOG_TRACE + "isEmptyHorseEgg(event.getItem()) ? " + isEmptyHorseEgg(event.getItem()));
    if (isHorseEgg(event.getItem()) || isEmptyHorseEgg(event.getItem())) {
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

  /**
   * 空のHorseEggを生成します.
   *
   * @param i 生成したいアイテム数 {@link int}
   * 
   * @return 空のHorseEgg {@link wacky.horseeggs.HorseEggs}
   */
  public ItemStack emptyHorseEgg(int i) {
    // 定義があるのは空だけ
    this.log.debug(PREF_LOG_TRACE + PREF_LOG_START + "HorseEggs.ItemStack:emptyHorseEgg(int)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]i=" + i);

    ItemStack egg = new ItemStack(Material.GHAST_SPAWN_EGG, i);
    this.log.trace(
        PREF_LOG_TRACE + "ItemStack egg <- new ItemStack(Material.GHAST_SPAWN_EGG, " + i + ")");

    ItemMeta meta = egg.getItemMeta();
    this.log.trace(PREF_LOG_TRACE + "ItemMeta meta <- egg.getItemMeta()");

    meta.setDisplayName("HorseEgg");
    this.log.trace(PREF_LOG_TRACE + "meta.setDisplayName(\"HorseEgg\")");

    List<String> lore = new ArrayList<String>();
    this.log.trace(PREF_LOG_TRACE + "List<String> lore <- new ArrayList<String>()");

    lore.add("Empty");
    this.log.trace(PREF_LOG_TRACE + "lore.add(\"Empty\")");
    this.log.trace(PREF_LOG_TRACE + "lore=" + lore.toString());

    meta.setLore(lore);
    this.log.trace(PREF_LOG_TRACE + "meta.setLore(lore)");
    this.log.trace(PREF_LOG_TRACE + "meta=" + meta.toString());

    egg.setItemMeta(meta);
    this.log.trace(PREF_LOG_TRACE + "egg.setItemMeta(meta)");
    this.log.trace(PREF_LOG_TRACE + "egg=" + egg.toString());

    log.trace(PREF_LOG_TRACE + "[RETURN PARAM]egg=" + egg.toString());
    log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.ItemStack:emptyHorseEgg(int)");
    return egg;
  }

  /**
   * アイテムが空のHorseEggか検査します.
   *
   * @param item アイテム
   * 
   * @return true：空のHorseEgg／false：空ではないHorseEggまたは、他のアイテム
   */
  public boolean isEmptyHorseEgg(ItemStack item) {
    // 1.13では白い馬卵が無い
    this.log
        .debug(PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.boolean:isEmptyHorseEgg(ItemStack)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]item=" + item.toString());

    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.GHAST_SPAWN_EGG ? "
        + (item.getType() == Material.GHAST_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.PIG_SPAWN_EGG ? "
        + (item.getType() == Material.PIG_SPAWN_EGG));
    if (item.getItemMeta() != null) {
      this.log
      .trace(PREF_LOG_TRACE + "item.getItemMeta().hasLore() ? " + item.getItemMeta().hasLore());
    }

    if (item.getType() == Material.GHAST_SPAWN_EGG
        || item.getType() == Material.PIG_SPAWN_EGG && item.getItemMeta().hasLore()) {
      this.log.trace(PREF_LOG_TRACE + "item.getItemMeta().getLore().get(0).equals(\"Empty\") ? "
          + item.getItemMeta().getLore().get(0).equals("Empty"));

      if (item.getItemMeta().getLore().get(0).equals("Empty")) {
        this.log.debug(PREF_LOG_DEBUG + "lore[0] is \"Empty\"");
        this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
        this.log
            .debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.boolean:isEmptyHorseEgg(ItemStack)");
        return true;
      }

      this.log.debug(PREF_LOG_DEBUG + "lore[0] is not \"Empty\"");
    }

    this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]false");
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.isEmptyHorseEgg(ItemStack):End");
    return false;
  }

  /**
   * アイテムがHorseEggか検査します.
   *
   * @param item アイテム
   * 
   * @return true：HorseEgg／false：他のアイテム
   */
  public boolean isHorseEgg(ItemStack item) {
    // 1.8まではダメージ値100、1.9ではメタ内にエンティティ記載あり
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.boolean:isHorseEgg(ItemStack)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]item=" + item.toString());

    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.HORSE_SPAWN_EGG ? "
        + (item.getType() == Material.HORSE_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.ZOMBIE_HORSE_SPAWN_EGG ? "
        + (item.getType() == Material.ZOMBIE_HORSE_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.SKELETON_HORSE_SPAWN_EGG ? "
        + (item.getType() == Material.SKELETON_HORSE_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.DONKEY_SPAWN_EGG ? "
        + (item.getType() == Material.DONKEY_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.MULE_SPAWN_EGG ? "
        + (item.getType() == Material.MULE_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.LLAMA_SPAWN_EGG ? "
        + (item.getType() == Material.LLAMA_SPAWN_EGG));
    this.log.trace(PREF_LOG_TRACE + "item.getType() == Material.TRADER_LLAMA_SPAWN_EGG ? "
        + (item.getType() == Material.TRADER_LLAMA_SPAWN_EGG));

    if (item.getType() == Material.HORSE_SPAWN_EGG
        || item.getType() == Material.ZOMBIE_HORSE_SPAWN_EGG
        || item.getType() == Material.SKELETON_HORSE_SPAWN_EGG
        || item.getType() == Material.DONKEY_SPAWN_EGG || item.getType() == Material.MULE_SPAWN_EGG
        || item.getType() == Material.LLAMA_SPAWN_EGG
        || item.getType() == Material.TRADER_LLAMA_SPAWN_EGG) {

      this.log
          .trace(PREF_LOG_TRACE + "item.getItemMeta().hasLore() ? " + item.getItemMeta().hasLore());
      if (item.getItemMeta().getLore() != null) {
        this.log.trace(PREF_LOG_TRACE + "item.getItemMeta().getLore().size() >= 3 ? "
            + (item.getItemMeta().getLore().size() >= 3));
      }

      if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().size() >= 3) {
        this.log.debug(PREF_LOG_DEBUG + "has lore and lore size is 3 or more");

        this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.boolean:isHorseEgg(ItemStack)");
        return true;
      }
      this.log.debug(PREF_LOG_DEBUG + "has not lore or lore size is less than 3");
    }
    this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]false");
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END + "HorseEggs.boolean:isHorseEgg(ItemStack)");
    return false;
  }

  /**
   * HorseEggを持った状態のプレイヤーが使用（右クリック）した対象のブロックに<br>
   * HorseEggを使用できるか検査します.
   *
   * @param block ブロック
   * 
   * @return true：大丈夫だよ／false：ダメです
   */
  public boolean isClickable(Block block) {
    // 名前変わりすぎ
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START + "HorseEggs.boolean:isClickable(Block)");
    this.log.trace(PREF_LOG_TRACE + "block state:" + block.getState() + ", block data:"
        + block.getBlockData() + ", material:" + block.getBlockData().getMaterial());
    if (block.getType().equals(Material.IRON_DOOR)
        || block.getType().equals(Material.IRON_TRAPDOOR)) {
      this.log.debug(PREF_LOG_DEBUG + "block is iron series");
      // 鉄シリーズは問答無用でfalse
      this.log.debug(PREF_LOG_DEBUG + "HorseEggs.isClickable(Block):End");
      return false;
    } else if (block.getState() instanceof Sign) {
      this.log.debug(PREF_LOG_DEBUG + "block is sign");
      // 看板は編集可・不可の状態が変化するので、動的に取得する
      this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM] !((Sign) block.getState()).isWaxed():"
          + !((Sign) block.getState()).isWaxed());
      this.log.debug(PREF_LOG_DEBUG + "HorseEggs.boolean:isClickable(Block)");
      return !((Sign) block.getState()).isWaxed();
    } else if (block.getState() instanceof Container || block.getState() instanceof EnderChest
        || block.getState() instanceof EnchantingTable || block.getState() instanceof CommandBlock
        || block.getState() instanceof DaylightDetector || block.getState() instanceof Jukebox
        || block.getState() instanceof ChiseledBookshelf || block.getBlockData() instanceof Door
        || block.getBlockData() instanceof TrapDoor || block.getBlockData() instanceof Bed
        || block.getBlockData() instanceof Gate || block.getBlockData() instanceof Cake
        || block.getBlockData() instanceof Switch || block.getBlockData() instanceof Repeater
        || block.getBlockData() instanceof Dispenser || block.getBlockData() instanceof Comparator
        || block.getBlockData() instanceof Hopper || block.getBlockData() instanceof NoteBlock
        || block.getBlockData() instanceof Chest || block.getBlockData() instanceof Grindstone
        || block.getBlockData() instanceof Furnace || block.getBlockData() instanceof Barrel
        || block.getBlockData().getMaterial().equals(Material.CRAFTING_TABLE)
        || block.getBlockData().getMaterial().equals(Material.ANVIL)
        || block.getBlockData().getMaterial().equals(Material.CHIPPED_ANVIL)
        || block.getBlockData().getMaterial().equals(Material.DAMAGED_ANVIL)
        || block.getBlockData().getMaterial().equals(Material.BEACON)
        || block.getBlockData().getMaterial().equals(Material.BREWING_STAND)
        || block.getBlockData().getMaterial().equals(Material.FURNACE_MINECART)
        || block.getBlockData().getMaterial().equals(Material.HOPPER_MINECART)
        || block.getBlockData().getMaterial().equals(Material.CAKE)
        || block.getBlockData().getMaterial().equals(Material.CANDLE_CAKE)
        || block.getBlockData().getMaterial().equals(Material.CHEST_MINECART)
        || block.getBlockData().getMaterial().equals(Material.COMMAND_BLOCK)
        || block.getBlockData().getMaterial().equals(Material.DAYLIGHT_DETECTOR)
        || block.getBlockData().getMaterial().equals(Material.RESPAWN_ANCHOR)
        || block.getBlockData().getMaterial().equals(Material.STONECUTTER)
        || block.getBlockData().getMaterial().equals(Material.CARTOGRAPHY_TABLE)
        || block.getBlockData().getMaterial().equals(Material.SMITHING_TABLE)
        || block.getBlockData().getMaterial().equals(Material.LOOM)
        || block.getBlockData().getMaterial().equals(Material.SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.RED_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.ORANGE_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.YELLOW_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.LIME_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.GREEN_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.CYAN_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.BLUE_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.PURPLE_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.MAGENTA_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.LIGHT_BLUE_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.PINK_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.BROWN_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.WHITE_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.GRAY_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.LIGHT_GRAY_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.BLACK_SHULKER_BOX)
        || block.getBlockData().getMaterial().equals(Material.ANVIL)) {
      // それ以外
      // ベルは触った場所が本体以外だとうまく動作しないが、場所を知る手立てがないため入れてない
      // コンポスターは最大状態以外だとうまく動作しないが、知る手立てがないため入れていない
      // エンティティ分類であるトロッコ系は現状止める手立てはないが、とりあえず入れてある
      this.log.debug(PREF_LOG_DEBUG + "others");
      this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
      this.log.debug(PREF_LOG_DEBUG + "HorseEggs.boolean:isClickable(Block)");
      return true;
    }
    this.log.debug(PREF_LOG_DEBUG + "last false");
    this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]false");
    this.log.debug(PREF_LOG_DEBUG + "HorseEggs.boolean:isClickable(Block)");
    return false;
  }

}
