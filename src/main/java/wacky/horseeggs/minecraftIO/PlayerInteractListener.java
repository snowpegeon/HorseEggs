package wacky.horseeggs.minecraftIO;

import static org.bukkit.Material.GLOWSTONE;
import static org.bukkit.Material.ICE;
import static org.bukkit.Material.OCHRE_FROGLIGHT;
import static org.bukkit.Material.PEARLESCENT_FROGLIGHT;
import static org.bukkit.Material.REDSTONE_BLOCK;
import static org.bukkit.Material.REDSTONE_LAMP;
import static org.bukkit.Material.SEA_LANTERN;
import static org.bukkit.Material.SHROOMLIGHT;
import static org.bukkit.Material.TNT;
import static org.bukkit.Material.VERDANT_FROGLIGHT;

import com.github.teruteru128.logger.Logger;
import com.saicone.rtag.RtagEditor;
import com.saicone.rtag.RtagItem;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mule;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import wacky.horseeggs.HorseEggs;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.LoreWriter.factory.LoreWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;
import wacky.horseeggs.eggData.factory.EggDataFactory;


/**
 * <p>
 * HorseEggsのプレイヤーインタラクトリスナークラス. {@link Listener}クラスを継承して、HorseEggsの<br>
 * {@link PlayerInteractEntityEvent}を実行する実装クラス.
 * </p>
 */
public class PlayerInteractListener implements Listener {

  /**
   * <p>
   * ログのプレフィックス(TRACE). {@link String}
   * </p>
   */
  private static final String PREF_LOG_TRACE = "[TRACE] ";

  /**
   * <p>
   * ログのプレフィックス(DEBUG). {@link String}
   * </p>
   */
  private static final String PREF_LOG_DEBUG = "[DEBUG] ";

  /**
   * <p>
   * ログの開始プレフィックス. {@link String}
   * </p>
   */
  private static final String PREF_LOG_START = "[START] ";

  /**
   * <p>
   * ログの終了プレフィックス. {@link String}
   * </p>
   */
  private static final String PREF_LOG_END = "[END] ";

  /**
   * <p>
   * プラグイン本体.<br> {@link org.bukkit.plugin.Plugin}を継承. {@link HorseEggs}
   * </p>
   */
  private final HorseEggs plugin;
  /**
   * <p>
   * ログを出力するためのLogger.<br> {@link Logger}
   * </p>
   */
  private final Logger log;

  private static final Set<Material> SUFFOCATING_MATERIALS = Collections.unmodifiableSet(
      EnumSet.of(TNT, ICE, GLOWSTONE, REDSTONE_BLOCK, SEA_LANTERN, SHROOMLIGHT, REDSTONE_LAMP,
          OCHRE_FROGLIGHT, PEARLESCENT_FROGLIGHT, VERDANT_FROGLIGHT));

  /**
   * <p>
   * コンストラクタ.<br> ここでこのイベントを鯖へ登録処理を実施.
   * </p>
   *
   * @param plugin {@link wacky.horseeggs.HorseEggs}
   * @param logger {@link com.github.teruteru128.logger.Logger}
   */
  public PlayerInteractListener(HorseEggs plugin, Logger logger) {
    this.plugin = plugin;
    this.log = logger;

    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START
        + "PlayerInteractListener.PlayerInteractListener(HorseEggs, Logger)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]plugin=" + plugin.toString());
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]_logger=" + logger);

    this.plugin.getServer().getPluginManager().registerEvents(this, plugin); // a
    this.log.trace(
        PREF_LOG_TRACE + "this.plugin.getServer().getPluginManager().registerEvents(this, plugin)");

    this.log
        .debug(PREF_LOG_DEBUG + PREF_LOG_END + "PlayerInteractListener.PlayerInteractListener()");
  }

  /**
   * <p>
   * プレーヤーがエンティティを右クリックしたときに呼び出されるイベントの処理.<br>
   * ここで、MOBをHorseEggs{@link EggDataBase}へのキャプチャーする処理を実施してる.
   * </p>
   *
   * @param event {@link org.bukkit.event.player.PlayerInteractEntityEvent}
   */
  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

    // このイベントは2回発生する
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START
        + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
    this.log.trace("[IN PARAM]event=" + event.getEventName());

    // 1.引数のイベントがキャンセル済か検査する
    this.log.trace(PREF_LOG_TRACE + "event.isCancelled() ? " + event.isCancelled());
    if (event.isCancelled()) {
      this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
          + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
      return;
    }

    // イベント中の情報を取得
    // このイベント中のプレイヤー
    Player player = event.getPlayer();
    this.log.trace(PREF_LOG_TRACE + "player=" + player);
    // このイベント中のプレイヤーのイベントリ情報
    PlayerInventory inv = player.getInventory();
//    this.log.trace(PREF_LOG_TRACE + "inv=" + inv);
    // このイベント中で「使用（右クリック）」されたエンティティ情報
    Entity entity = event.getRightClicked();
//    this.log.trace(PREF_LOG_TRACE + "entity=" + entity);
    // このイベント中のプレイヤーのメインハンドのアイテムスタック
    ItemStack itemInHand = player.getInventory().getItemInMainHand();
    this.log.trace(PREF_LOG_TRACE + "itemInHand=" + itemInHand);

    // 2-1.使用（右クリック）のアイテムがキャッチ済HorseEggかを検査する
    // キャッチ済みの場合はリリース処理を行う。
    // 馬卵を他のエンティティ、馬に使ったとき
    // 馬エンティティ格納用
    AbstractHorse horse;
    this.log.trace(PREF_LOG_TRACE + "horse");
    this.log
        .trace(PREF_LOG_TRACE + "plugin.isHorseEgg(itemInHand) ? " + plugin.isHorseEgg(itemInHand));
    if (plugin.isHorseEgg(itemInHand)) {
      // 子馬が生まれないように
      event.setCancelled(true);
      this.log.trace(PREF_LOG_TRACE + "event.setCancelled(true)");
      this.log.trace(PREF_LOG_TRACE + "event.getHand() == EquipmentSlot.OFF_HAND ? "
          + (event.getHand() == EquipmentSlot.OFF_HAND));
      // 2-2.使用（右クリック）がオフハンドか検査する
      if (event.getHand() == EquipmentSlot.OFF_HAND) {
        // オフハンド用の判定は拒否、収納→即放出と増殖がある
        // オフハンドだった場合は処理を終了
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
        return;
      }

      // 2-3.HorseEggs権限（リリース）を検査する
      // プレイヤーがHorseEggsで馬をリリースする権限があるか検査
      this.log.trace(PREF_LOG_TRACE + "!player.hasPermission(\"horseeggs.release\") ? "
          + player.hasPermission("horseeggs.release"));
      if (!player.hasPermission("horseeggs.release")) {
        // 権限が無い場合は処理を終了
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
        return;
      }

      // 「使用（右クリック）」された対象のロケーションを取得
      Location loc = event.getRightClicked().getLocation();
      this.log.trace(PREF_LOG_TRACE + "loc=" + loc);
      // 馬のリリースを行う
      ReleaseHorse.releseHorseFromHorseEgg(itemInHand, loc, log);
      this.log.debug(PREF_LOG_DEBUG + "ReleaseHorse.releseHorseFromHorseEgg.");
      // アイテムの現在合計値を取得
      int amount = itemInHand.getAmount();
      this.log.trace(PREF_LOG_TRACE + "amount=" + amount);

      // プラグイン設定で使い切りの場合
      // 2-4.HorseEggsが使い切りか検査する
      this.log.trace(PREF_LOG_TRACE + "plugin.config.getBoolean(\"single-use\") ? "
          + plugin.config.getBoolean("single-use"));
      this.log.trace(PREF_LOG_TRACE + "amount == 1 ?" + (amount == 1));
      this.log
          .trace(PREF_LOG_TRACE + "itemInHand.getAmount() == 1 ? " + (itemInHand.getAmount() == 1));
      if (plugin.config.getBoolean("single-use")) {
        if (amount == 1) {
          // 2-5.アイテムが残り１つか検査する
          // 残り１つの場合は該当のインベントリスロットを空にする
          inv.setItemInMainHand(null);
          this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(null)");
        } else {
          // 残り１つ以外の場合は、１つ減らす
          itemInHand.setAmount(amount - 1);
          this.log.trace(PREF_LOG_TRACE + "itemInHand.setAmount(amount - 1)");
        }

      } else if (itemInHand.getAmount() == 1) {
        // 使い切りでは無い場合
        // 2-6.アイテムが残り１つか検査する
        // 残り1つの場合
        // チャッチ済のHorsEggsを空のHorseEggsに置換する
        inv.setItemInMainHand(plugin.emptyHorseEgg(1));
        this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(plugin.emptyHorseEgg(1))");

      } else {
        // 残り2つ以上の場合
        // 現在合計値を1つ減らす
        itemInHand.setAmount(amount - 1);
        this.log.trace(PREF_LOG_TRACE + "itemInHand.setAmount(amount - 1)");

        // インベントリを更新する
        inv.setItemInMainHand(itemInHand);
        this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(itemInHand)");

        // 2-7.インベントリが一杯か検査する
        this.log.trace(PREF_LOG_TRACE + "inv.firstEmpty() == -1 || inv.firstEmpty() >= 36 ? "
            + (inv.firstEmpty() == -1 || inv.firstEmpty() >= 36));
        if (inv.firstEmpty() == -1 || inv.firstEmpty() >= 36) {
          // インベントリが一杯の場合
          // プレイヤーの正面にアイテムをドロップする
          loc.add(0, 0.5, 0);
          this.log.trace(PREF_LOG_TRACE + "loc.add(0, 0.5, 0)");
          entity.getWorld().dropItem(loc, plugin.emptyHorseEgg(1));
          this.log
              .trace(PREF_LOG_TRACE + "entity.getWorld().dropItem(loc, plugin.emptyHorseEgg(1))");
        } else {
          // チャッチ済のHorsEggsを空のHorseEggsに置換する
          player.getInventory().addItem(plugin.emptyHorseEgg(1));
          this.log.trace(PREF_LOG_TRACE + "player.getInventory().addItem(plugin.emptyHorseEgg(1))");
        }
      }
      // 馬のリリース処理終わり
      this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
          + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
      return;

    } else if (plugin.isEmptyHorseEgg(itemInHand)) {
      // 3-1.使用（右クリック）のアイテムが空のHorseEggかを検査する
      // 空のHorseEggだった場合は対象のウマをアイテムとして取得する
      this.log.trace(PREF_LOG_TRACE + "plugin.isEmptyHorseEgg(itemInHand) ? "
          + (plugin.isEmptyHorseEgg(itemInHand)));
      // 何かが乗ってる時は回収不能、子馬・出した直後もNG
      // 馬に卵を使ったことになるんだとか
      // イベントにキャンセルをセットする
      event.setCancelled(true);
      this.log.trace(PREF_LOG_TRACE + "event.setCancelled(true)");

      // 3-2.使用（右クリック）がオフハンドか検査する
      // オフハンド用の判定は拒否
      this.log.trace(PREF_LOG_TRACE + "event.getHand() == EquipmentSlot.OFF_HAND ? "
          + (event.getHand() == EquipmentSlot.OFF_HAND));
      if (event.getHand() == EquipmentSlot.OFF_HAND) {
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
        return;
      }

      // 3-3.使用（右クリック）した対象のエンティティがウマ系か検査する
      this.log.trace(PREF_LOG_TRACE + "entity instanceof AbstractHorse ? "
          + (entity instanceof AbstractHorse));
      if (entity instanceof AbstractHorse) {
        // 馬系の場合
        // 馬エンティティに変換
        horse = (AbstractHorse) entity;
        this.log.trace(PREF_LOG_TRACE + "horse=" + horse);
      } else {
        // 馬系以外は処理を終了
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
        return;
      }

      // 3-4.使用（右クリック）した対象のウマがキャプチャー出来る対象か検査する
      // 対象の馬エンティティが「大人」かつ、「騎乗者：なし」かつ、「熟年」
      this.log.trace(PREF_LOG_TRACE + "horse.isAdult() ? " + horse.isAdult());
      this.log.trace(
          PREF_LOG_TRACE + "horse.getPassengers().isEmpty() ? " + horse.getPassengers().isEmpty());
      this.log.trace(PREF_LOG_TRACE + "horse.getAge() < 5980 ? " + (horse.getAge() < 5980));
      if (horse.isAdult() && horse.getPassengers().isEmpty() && horse.getAge() < 5980) {

        // 3-5.HorseEggs権限（キャプチャー）を検査する
        // プレイヤーがHorseEggsで馬を捕獲する権限があるか
        this.log.trace(PREF_LOG_TRACE + "!player.hasPermission(\"horseeggs.capture\") ? "
            + (!player.hasPermission("horseeggs.capture")));
        if (!player.hasPermission("horseeggs.capture")) {
          // 権限が無い場合は処理を終了
          this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
              + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
          return;
        }

        // 馬系の何であるかを識別
        EntityType type = horse.getType();
        EggDataBase eggData = EggDataFactory.newEggData(type, horse);
        if (Objects.isNull(eggData)) {
          this.log.warn("This EntityType is null.");
          return;
        }
        eggData.setLoreList(LoreWriterFactory.newLoreWriter(type, eggData).generateLore(eggData));

        this.log.trace(PREF_LOG_TRACE + "type=" + type);
        this.log.trace(PREF_LOG_TRACE + "eggData=" + eggData);
        ItemStack horseegg = new ItemStack(eggData.getFilledEggMaterial());

        this.log.trace(PREF_LOG_TRACE + "horseegg=" + horseegg);

        // 保存タグ情報の構築
        RtagEditor<ItemStack, RtagItem> editor = new RtagItem(horseegg);

        editor.set(eggData.getIdNamespaceMap());
        this.log.trace(PREF_LOG_TRACE + "IdNamespaceMap: " + eggData.getIdNamespaceMap());

        editor.set(eggData.getEntityTagMap());
        this.log.trace(PREF_LOG_TRACE + "EntityTagMap: " + eggData.getEntityTagMap());

        Location loc = horse.getLocation();
        this.log.trace(PREF_LOG_TRACE + "Location loc <- horse.getLocation()");
        loc.add(0, 0.5, 0);
        this.log.trace(PREF_LOG_TRACE + "loc.add(0, 0.5, 0)");
        this.log.trace(PREF_LOG_TRACE + "loc=" + loc);

        // チェスト付き馬達のチェストの中身バラマキ
        if (entity instanceof ChestedHorse) {
          this.log.debug("entity is ChestedHorse");

          // ロバかラバの場合、アイテム化しないよう先頭にある鞍をあらかじめ除外しておく
          AbstractHorse abHorse = null;
          if (entity instanceof Donkey || entity instanceof Mule) {
            this.log.trace("entity is Donkey or Mule.");
            abHorse = (AbstractHorse) entity;

            // サドルがついてる場合は、外す
            this.log.trace("hasSaddle: " + Objects.nonNull(abHorse.getInventory().getSaddle()));
            if (Objects.nonNull(abHorse.getInventory().getSaddle())) {
              this.log.trace("this entity has Saddle.");
              abHorse.getInventory().setSaddle(null);
            }
          }
          for (ItemStack storageItem : horse.getInventory().getStorageContents()) {
            if (storageItem != null) {
              this.log.debug("StorageItem is not null.Item drop!");
              entity.getWorld().dropItem(loc, storageItem);
            }
          }
          this.log.debug("HorseInventory Clear.");
          horse.getInventory().clear();
        }

        // 構築したタグ情報をインベントリ保存する
        editor.set(eggData.getHorseEggTagDataMap());
        this.log.trace(PREF_LOG_TRACE + "HorseEggTagDataMap: " + eggData.getHorseEggTagDataMap());
        editor.load();
        this.log.trace(PREF_LOG_TRACE + "RTagEditor.load().");

        ItemMeta meta = horseegg.getItemMeta();
        // ItemMetaとれなかったら、そもそもおかしいので処理終了
        if (Objects.isNull(meta)) {
          this.log.info("horseEgg ItemMeta is NULL.End.");
          return;
        }
        LoreWriter loreWriter = LoreWriterFactory.newLoreWriter(eggData.getEntityType(), eggData);
        this.log.trace(PREF_LOG_TRACE + "ItemMeta meta <- horseegg.getItemMeta()");
        meta.setDisplayName(Objects.nonNull(eggData.getName()) ? eggData.getName() : eggData.getDisplayName());

        this.log.trace(PREF_LOG_TRACE + "meta.setDisplayName(eggData.getDisplayName())");
        meta.setLore(loreWriter.getLoreList());

        this.log.trace(PREF_LOG_TRACE + "meta.setLore(eggData.getLoreList())");
        this.log.trace(PREF_LOG_TRACE + "meta=" + meta);

        horseegg.setItemMeta(meta);
        this.log.trace(PREF_LOG_TRACE + "horseegg.setItemMeta(meta)");
        this.log.trace(PREF_LOG_TRACE + "horseegg=" + horseegg);

        this.log.trace(PREF_LOG_TRACE + "inv.getItemInMainHand().getAmount() == 1 ? "
            + (inv.getItemInMainHand().getAmount() == 1));
        if (inv.getItemInMainHand().getAmount() == 1) {
          inv.setItemInMainHand(horseegg);
          this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(horseegg)");

        } else {
          itemInHand.setAmount(itemInHand.getAmount() - 1);
          this.log.trace(PREF_LOG_TRACE + "itemInHand.setAmount(itemInHand.getAmount() - 1)");

          this.log.trace(PREF_LOG_TRACE + "inv.firstEmpty() == -1 ? " + (inv.firstEmpty() == -1));
          this.log.trace(PREF_LOG_TRACE + "inv.firstEmpty() >= 36 ? " + (inv.firstEmpty() >= 36));
          if (inv.firstEmpty() == -1 || inv.firstEmpty() >= 36) {
            horse.getWorld().dropItem(loc, horseegg);
            this.log.trace(PREF_LOG_TRACE + "horse.getWorld().dropItem(loc, horseegg)");
          } else {
            inv.addItem(horseegg);
            this.log.trace(PREF_LOG_TRACE + "inv.addItem(horseegg)");
          }
        }
        horse.remove();
        this.log.trace(PREF_LOG_TRACE + "horse.remove()");
      }
    }

    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
        + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
  }

  /**
   * <p>
   * プレイヤーがオブジェクトまたは空気に対して呼び出されるイベント処理<br> ハンドごとに 1 回発生する可能性があります。 <br> ハンドは getHand()
   * を使用して決定できます。<br>
   * <br>
   * ここでは、HorseEggs{@link EggDataBase}に入った馬のリリース処理を行っている.
   * </p>
   *
   * @param event {@link org.bukkit.event.player.PlayerInteractEvent}
   */
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_START
        + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
    this.log.debug(PREF_LOG_DEBUG + "[IN PARAM]event=" + event.toString());

    ItemStack item = event.getItem();
    this.log.trace(PREF_LOG_TRACE + "ItemStack item = event.getItem()");

    this.log.trace(PREF_LOG_TRACE + "item == null ? " + (item == null));
    if (item == null) {
      this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
          + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
      return;
    }

    this.log
        .trace(PREF_LOG_TRACE + "plugin.isEmptyHorseEgg(item) ? " + (plugin.isEmptyHorseEgg(item)));
    this.log.trace(PREF_LOG_TRACE + "plugin.isHorseEgg(item) ? " + plugin.isHorseEgg(item));

    if (plugin.isEmptyHorseEgg(item)) {
      event.setCancelled(true);
      this.log.trace(PREF_LOG_TRACE + "event.setCancelled(true)");
      this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
          + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
      return;
    } else if (plugin.isHorseEgg(item)) {
      event.setUseItemInHand(Result.DENY);
      this.log.trace(PREF_LOG_TRACE + "event.setUseItemInHand(Result.DENY)");

      this.log.trace(
          PREF_LOG_TRACE + "event.isCancelled() ? " + (event.useInteractedBlock() == Result.DENY));
      if (event.useInteractedBlock() == Result.DENY) {
        // まさかの水源、溶岩源を右クリックした場合、cancelledのはずだけどスポーンエッグは使える
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
        return;
      }

      this.log.trace(PREF_LOG_TRACE + "event.getAction() != Action.RIGHT_CLICK_BLOCK ? "
          + (event.getAction() != Action.RIGHT_CLICK_BLOCK));
      if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
        return;
      }

      Block clickedBlock = event.getClickedBlock();
      this.log.trace(PREF_LOG_TRACE + "clickedBlock ? " + clickedBlock);
      // クリックしたブロックがとれなかったら、処理を強制停止
      if (Objects.isNull(clickedBlock)) {
        this.log.info("clickedBlock is null.End.");
        return;
      }

      Player player = event.getPlayer();
      this.log.trace(PREF_LOG_TRACE + "Player player <- event.getPlayer()");
      this.log.trace(PREF_LOG_TRACE + "!player.isSneaking() ? " + !player.isSneaking());
      this.log.trace(PREF_LOG_TRACE + "plugin.isClickable(event.getClickedBlock()) ? "
          + plugin.isClickable(clickedBlock));
      if (!player.isSneaking() && plugin.isClickable(clickedBlock)) {
        // ブロック優先
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
        return;
      }

      event.setUseInteractedBlock(Result.DENY);
      this.log.trace(PREF_LOG_TRACE + "event.setUseInteractedBlock(Result.DENY)");

      this.log.trace(PREF_LOG_TRACE + "!player.hasPermission(\"horseeggs.release\") ? "
          + !player.hasPermission("horseeggs.release"));
      if (!player.hasPermission("horseeggs.release")) {
        event.setCancelled(true);
        this.log.trace(PREF_LOG_TRACE + "event.setCancelled(true)");
        this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
            + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
        return;
      }

      // 馬がめり込まないようにしたい
      Boolean[][] blocks = new Boolean[5][5];
      this.log.trace(PREF_LOG_TRACE + "Boolean[][] blocks <- new Boolean[5][5]");
      Arrays.fill(blocks[0], false);
      this.log.trace(PREF_LOG_TRACE + "Arrays.fill(blocks[0], false)");
      Arrays.fill(blocks[4], false);
      this.log.trace(PREF_LOG_TRACE + "Arrays.fill(blocks[4], false)");

      this.log.trace(PREF_LOG_TRACE + "[LOOP START](int i = 1; i <= 3; i++)");
      Block centerBlock = clickedBlock.getRelative(event.getBlockFace());
      this.log.trace(PREF_LOG_TRACE
          + "Block centerBlock <- event.getClickedBlock().getRelative(event.getBlockFace())");
      boolean canSpawnCenter = true;
      this.log.trace(PREF_LOG_TRACE + "boolean canSpawnCenter <- true");
      for (int i = 1; i <= 3; i++) {
        Arrays.fill(blocks[i], false);

        this.log.trace(PREF_LOG_TRACE + "[LOOP START](int j = 1; j <= 3; j++)");
        for (int j = 1; j <= 3; j++) {
          blocks[i][j] = isSuffocating(centerBlock.getRelative(i - 2, 1, j - 2).getType());
          this.log.trace(PREF_LOG_TRACE + "blocks[i][j] <- "
              + "isSuffocating(centerBlock.getRelative(i - 2, 1, j - 2).getType())");

          this.log.trace(PREF_LOG_TRACE + "blocks[i][j] ? " + blocks[i][j]);
          if (blocks[i][j]) {
            canSpawnCenter = false;
            this.log.trace(PREF_LOG_TRACE + "canSpawnCenter <- false");
          }
        }
        this.log.trace(PREF_LOG_TRACE + "[LOOP END](int j = 1; j <= 3; j++)");

      }
      this.log.trace(PREF_LOG_TRACE + "[LOOP END](int i = 1; i <= 3; i++)");

      Location loc = centerBlock.getLocation();
      this.log.trace(PREF_LOG_TRACE + "Location loc <- centerBlock.getLocation()");
      this.log.trace(PREF_LOG_TRACE + "canSpawnCenter ? " + canSpawnCenter);
      if (canSpawnCenter) {
        loc.add(0.5, 0, 0.5);
        this.log.trace(PREF_LOG_TRACE + "loc.add(0.5, 0, 0.5)");

        this.log.trace(PREF_LOG_TRACE + "ReleaseHorse.releseHorseFromHorseEgg(item, loc, log)");
        ReleaseHorse.releseHorseFromHorseEgg(item, loc, log);

      } else {
        this.log.trace(PREF_LOG_TRACE + "search:");
        search:
          {
            this.log.trace(PREF_LOG_TRACE + "[LOOP START]for (int i = 0; i < 3; i++)");
            for (int i = 0; i < 3; i++) {
              // どっかにブロック有り。
              this.log.trace(PREF_LOG_TRACE + "[LOOP START]for (int j = 0; j < 3; j++)");
              for (int j = 0; j < 3; j++) {
                // 周囲9マス()にブロックが無いか
                boolean canSpawn = !blocks[i][j] && !blocks[i][j + 1] && !blocks[i][j + 2]
                    && !blocks[i + 1][j] && !blocks[i + 1][j + 1] && !blocks[i + 1][j + 2]
                    && !blocks[i + 2][j] && !blocks[i + 2][j + 1] && !blocks[i + 2][j + 2];

                this.log.trace(PREF_LOG_TRACE + "canSpawn ? " + canSpawn);
                if (canSpawn) {
                  loc.add(i * 0.5, 0, j * 0.5);
                  this.log.trace(PREF_LOG_TRACE + "loc.add(i * 0.5, 0, j * 0.5)");

                  this.log.trace(
                      PREF_LOG_TRACE + "ReleaseHorse.releseHorseFromHorseEgg(item, loc, log)");
                  ReleaseHorse.releseHorseFromHorseEgg(item, loc, log);

                  this.log.trace(PREF_LOG_TRACE + "break search");
                  break search;
                }
              }
              this.log.trace(PREF_LOG_TRACE + "[LOOP END]for (int j = 0; j < 3; j++)");
            }
            this.log.trace(PREF_LOG_TRACE + "[LOOP END]for (int i = 0; i < 3; i++)");
            // スポーン失敗。
            event.setCancelled(true);
            this.log.trace(PREF_LOG_TRACE + "event.setCancelled(true)");

            this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
                + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
            return;
          }
      }

      // オフハンド対策
      this.log.trace(PREF_LOG_TRACE + "plugin.config.getBoolean(\"single-use\") ? "
          + (plugin.config.getBoolean("single-use")));
      this.log.trace(PREF_LOG_TRACE + "item.getAmount() == 1 ? " + (item.getAmount() == 1));

      PlayerInventory inv = player.getInventory();
      this.log.trace(PREF_LOG_TRACE + "PlayerInventory inv <- player.getInventory()");
      int amount = item.getAmount();
      this.log.trace(PREF_LOG_TRACE + "int amount = item.getAmount()");
      if (plugin.config.getBoolean("single-use")) {
        this.log.trace(PREF_LOG_TRACE + "amount == 1 ? " + (amount == 1));
        if (amount == 1) {

          this.log.trace(PREF_LOG_TRACE + "event.getHand() == EquipmentSlot.HAND ? "
              + (event.getHand() == EquipmentSlot.HAND));
          if (event.getHand() == EquipmentSlot.HAND) {
            inv.setItemInMainHand(null);
            this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(null)");

          } else {
            inv.setItemInOffHand(null);
            this.log.trace(PREF_LOG_TRACE + "inv.setItemInOffHand(null)");
          }
        } else {
          item.setAmount(amount - 1);
          this.log.trace(PREF_LOG_TRACE + "item.setAmount(amount - 1)");
        }
      } else if (item.getAmount() == 1) {
        this.log.trace(PREF_LOG_TRACE + "event.getHand() == EquipmentSlot.HAND ? "
            + (event.getHand() == EquipmentSlot.HAND));
        if (event.getHand() == EquipmentSlot.HAND) {
          inv.setItemInMainHand(plugin.emptyHorseEgg(1));
          this.log.trace(PREF_LOG_TRACE + "inv.setItemInMainHand(plugin.emptyHorseEgg(1))");

        } else {
          inv.setItemInOffHand(plugin.emptyHorseEgg(1));
          this.log.trace(PREF_LOG_TRACE + "inv.setItemInOffHand(plugin.emptyHorseEgg(1))");
        }
      } else {
        item.setAmount(amount - 1);
        this.log.trace(PREF_LOG_TRACE + "item.setAmount(amount - 1)");

        this.log.trace(PREF_LOG_TRACE + "inv.firstEmpty() == -1 ? " + (inv.firstEmpty() == -1));
        this.log.trace(PREF_LOG_TRACE + "inv.firstEmpty() >= 36 ? " + (inv.firstEmpty() >= 36));
        if (inv.firstEmpty() == -1 || inv.firstEmpty() >= 36) {
          loc.add(0, 0.5, 0);
          this.log.trace(PREF_LOG_TRACE + "loc.add(0, 0.5, 0)");

          World world = loc.getWorld();
          if (Objects.isNull(world)) {
            this.log.error("WorldData is Null!End.");
            return;
          }
          world.dropItem(loc, plugin.emptyHorseEgg(1));
          this.log.trace(PREF_LOG_TRACE + "loc.getWorld().dropItem(loc, plugin.emptyHorseEgg(1))");
        } else {
          inv.addItem(plugin.emptyHorseEgg(1));
          this.log.trace(PREF_LOG_TRACE + "inv.addItem(plugin.emptyHorseEgg(1))");
        }
      }
      this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
          + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
    }
    this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
        + "PlayerInteractListener.void:onPlayerInteract(PlayerInteractEvent)");
  }

  /**
   * <p>
   * マテリアルが窒息するものか検査します.
   * </p>
   *
   * @param mat {@link org.bukkit.Material}
   * @return {@link boolean} 窒息するか.
   */
  private boolean isSuffocating(Material mat) {
    this.log.debug(
        PREF_LOG_DEBUG + PREF_LOG_START + "PlayerInteractListener.boolean:isSuffocating(Material)");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]mat=" + mat.toString());

    this.log.trace(PREF_LOG_TRACE + "mat.isOccluding() ? " + mat.isOccluding());
    this.log.trace(
        PREF_LOG_TRACE + "SUFFOCATING_MATERIALS.contains(mat): " + SUFFOCATING_MATERIALS.contains(
            mat));
    if (mat.isOccluding()) {
      // 窒息する透過ブロック
      this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
      this.log.debug(
          PREF_LOG_DEBUG + PREF_LOG_END + "PlayerInteractListener.boolean:isSuffocating(Material)");
      return true;
    } else if (SUFFOCATING_MATERIALS.contains(mat)) {
      this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
      this.log.debug(
          PREF_LOG_DEBUG + PREF_LOG_END + "PlayerInteractListener.boolean:isSuffocating(Material)");
      return true;
    }
    this.log.trace(PREF_LOG_TRACE + "[RETURN PARAM]false");
    this.log.debug(
        PREF_LOG_DEBUG + PREF_LOG_END + "PlayerInteractListener.boolean:isSuffocating(Material)");
    return false;
  }
}
