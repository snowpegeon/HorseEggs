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
import java.util.Objects;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
    this.log.trace(PREF_LOG_TRACE + "inv=" + inv);
    // このイベント中で「使用（右クリック）」されたエンティティ情報
    Entity entity = event.getRightClicked();
    this.log.trace(PREF_LOG_TRACE + "entity=" + entity);
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
        EggDataBase eggData = new EggDataFactory().newEggData(type, horse);
        eggData.setLoreList(
            new LoreWriterFactory().newLoreWriter(type, eggData).generateLore(entity));
        if (Objects.isNull(eggData)) {
          this.log.error("This EntityType is null.");
          return;
        }

        this.log.trace(PREF_LOG_TRACE + "type=" + type);
        this.log.trace(PREF_LOG_TRACE + "eggData=" + eggData);
        ItemStack horseegg = new ItemStack(eggData.getEmptyEggMaterial());
//        ItemStack horseegg = null;
//        switch (type) {
//          // 見た目を馬卵にする方法
//          case HORSE:
//            // 馬卵
//            horseegg = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
//            break;
//          case SKELETON_HORSE:
//            // 骨馬卵
//            horseegg = new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG, 1);
//            break;
//          case ZOMBIE_HORSE:
//            // 腐馬卵
//            horseegg = new ItemStack(Material.ZOMBIE_HORSE_SPAWN_EGG, 1);
//            break;
//          case DONKEY:
//            // ロバ卵
//            horseegg = new ItemStack(Material.DONKEY_SPAWN_EGG, 1);
//            break;
//          case MULE:
//            // 騾馬卵
//            horseegg = new ItemStack(Material.MULE_SPAWN_EGG, 1);
//            break;
//          case LLAMA:
//            // ラマ卵
//            horseegg = new ItemStack(Material.LLAMA_SPAWN_EGG, 1);
//            break;
//          case TRADER_LLAMA:
//            // 商人のラマ
//            horseegg = new ItemStack(Material.TRADER_LLAMA_SPAWN_EGG, 1);
//            break;
//          default:
//            // 判別できない場合は処理を終了
//            this.log.trace(PREF_LOG_TRACE + "horseegg is not Horse.");
//            this.log.debug(PREF_LOG_DEBUG + PREF_LOG_END
//                + "PlayerInteractListener.void:onPlayerInteractEntity(PlayerInteractEntityEvent)");
//            return;
//        }
        this.log.trace(PREF_LOG_TRACE + "horseegg=" + horseegg);

        // 保存タグ情報の構築
        ItemStack stack = horseegg.clone();
        RtagEditor<ItemStack, RtagItem> editor = new RtagItem(stack);

        editor.set(eggData.getIdNamespaceMap());
        this.log.trace(PREF_LOG_TRACE + "IdNamespaceMap: " + eggData.getIdNamespaceMap());
//        CompoundTag id = new CompoundTag();
//        this.log.trace(PREF_LOG_TRACE + "CompoundTag id <- new CompoundTag()");
//        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(horseegg);
//        id.putString("id", "minecraft:" + type.toString().toLowerCase());
//        this.log.trace(PREF_LOG_TRACE + "Put to id:id=" + id.toString());

        editor.set(eggData.getEntityTagMap());
        this.log.trace(PREF_LOG_TRACE + "EntityTagMap: " + eggData.getEntityTagMap());
//        CompoundTag tag = new CompoundTag();
//        this.log.trace(PREF_LOG_TRACE + "CompoundTag tag <- new CompoundTag()");
//        tag.put("EntityTag", id);
//        this.log.trace(PREF_LOG_TRACE + "Put to tag:tag=" + tag.toString());

//        CompoundTag horseData = new CompoundTag();
//        this.log.trace(PREF_LOG_TRACE + "CompoundTag horseData <- new CompoundTag()");
//        // 名前
//        this.log.trace(
//            PREF_LOG_TRACE + "horse.getCustomName() != null ? " + (horse.getCustomName() != null));
//        if (horse.getCustomName() != null) {
//          horseData.putString("Name", horse.getCustomName());
//          this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//        }
//
//        // 体力
//        horseData.putDouble("Health", horse.getHealth());
//        this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//        horseData.putDouble("MaxHealth",
//            horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
//        this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//        List<String> loreList = new ArrayList<String>();
//        this.log.trace(PREF_LOG_TRACE + "List<String> loreList <- new ArrayList<String>()");
//
//        loreList.add("HP: " + (int) horse.getHealth() + "/"
//            + (int) horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
//        this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//
//        // 速度、43倍すると実際の速度に
//        CompoundTag tag2 = new CompoundTag();
//        this.log.trace(PREF_LOG_TRACE + "CompoundTag tag2 <- new CompoundTag()");
//        ((CraftAbstractHorse) horse).getHandle().addAdditionalSaveData(tag2);
//        this.log.trace(PREF_LOG_TRACE
//            + "((CraftAbstractHorse) horse).getHandle().addAdditionalSaveData(tag2)");
//        this.log.trace(PREF_LOG_TRACE + "tag2=" + tag2.toString());
//        ListTag attributes = tag2.getList("Attributes", 10);
//        this.log.trace(PREF_LOG_TRACE + "ListTag attributes <- tag2.getList(\"Attributes\", 10)");
//        this.log.trace(PREF_LOG_TRACE + "attributes=" + attributes.toString());
//
//        this.log.trace(PREF_LOG_TRACE + "[LOOP START]for (int i = 0; i < attributes.size(); i++)");
//        for (int i = 0; i < attributes.size(); i++) {
//          CompoundTag attr = (CompoundTag) attributes.get(i);
//          if (attr.getString("Name").equals("minecraft:generic.movement_speed")) {
//            Double speed = attr.getDouble("Base");
//            horseData.putDouble("Speed", speed);
//            if (Double.toString(speed * 43).length() > 6) {
//              loreList.add("Speed: " + Double.toString(speed * 43).substring(0, 6));
//            } else {
//              loreList.add("Speed: " + Double.toString(speed * 43));
//            }
//          }
//        }
//        this.log.trace(PREF_LOG_TRACE + "horseData=" + horseData.toString());
//        this.log.trace(PREF_LOG_TRACE + "list=" + loreList.toString());
//        this.log.trace(PREF_LOG_TRACE + "[LOOP END]for (int i = 0; i < attributes.size(); i++)");
//
//        // 跳躍力、NBTにのみ書かれるべき
//        Double jump = horse.getJumpStrength();
//        horseData.putDouble("Jump", jump);
//        this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//        // ジャンプ高度算出
//        // from Zyin's HUD、ジャンプ高度
//        double jumpHeight = 0;
//        this.log.trace(PREF_LOG_TRACE + "[LOOP START]while (jump > 0)");
//        while (jump > 0) {
//          jumpHeight += jump;
//          jump -= 0.08;
//          jump *= 0.98;
//        }
//        this.log.trace(PREF_LOG_TRACE + "[LOOP END]while (jump > 0)");
//
//        if (Double.toString(jumpHeight).length() > 5) {
//          loreList.add("Height: " + Double.toString(jumpHeight).substring(0, 5));
//        } else {
//          loreList.add("Height: " + Double.toString(jumpHeight));
//        }
//        this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//
//        horseData.putString("Type", horse.getType().toString());
//        this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//        // TODO getVariantが非推奨型のため、取得方式を検討する。
//        horseData.putString("Variant", horse.getVariant().toString());
//        this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//        this.log.trace(PREF_LOG_TRACE + "horse.getType() == EntityType.LLAMA ? "
//            + (horse.getType() == EntityType.LLAMA));
//        this.log.trace(PREF_LOG_TRACE + "horse.getType() == EntityType.TRADER_LLAMA ? "
//            + (horse.getType() == EntityType.TRADER_LLAMA));
//        if (horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA) {
//          horseData.putInt("Strength", ((Llama) horse).getStrength());
//          loreList.add("Strength: " + ((Llama) horse).getStrength());
//
//          horseData.putString("Color", ((Llama) horse).getColor().toString());
//          loreList.add(((Llama) horse).getColor().toString());
//
//          this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//          this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//
//        } else if (horse.getType() == EntityType.HORSE) {
//          // 馬
//          horseData.putString("Color", ((Horse) horse).getColor().toString());
//          horseData.putString("Style", ((Horse) horse).getStyle().toString());
//          loreList.add(
//              ((Horse) horse).getColor().toString() + "/" + ((Horse) horse).getStyle().toString());
//
//          this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//          this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//        }

        Location loc = horse.getLocation();
        this.log.trace(PREF_LOG_TRACE + "Location loc <- horse.getLocation()");
        loc.add(0, 0.5, 0);
        this.log.trace(PREF_LOG_TRACE + "loc.add(0, 0.5, 0)");
        this.log.trace(PREF_LOG_TRACE + "loc=" + loc);

//        this.log.trace(PREF_LOG_TRACE + "horse.isTamed() ? " + horse.isTamed());
//        if (horse.isTamed()) {
//          // 飼いならした人、UUIDを内部的には使用する。
//          this.log
//              .trace(PREF_LOG_TRACE + "horse.getOwner() != null ? " + (horse.getOwner() != null));
//          if (horse.getOwner() != null) {
//            AnimalTamer owner = horse.getOwner();
//            horseData.putLong("UUIDMost", owner.getUniqueId().getMostSignificantBits());
//            horseData.putLong("UUIDLeast", owner.getUniqueId().getLeastSignificantBits());
//            loreList.add("Owner: " + owner.getName());
//
//            this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//            this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//          }
//
//          AbstractHorseInventory horseInv = horse.getInventory();
//          // サドル
//          horseData.putBoolean("Saddle", horseInv.getSaddle() != null);
//          this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//          String str1 = horseInv.getSaddle() == null ? "" : "[SADDLE]";
//          this.log.trace(PREF_LOG_TRACE + "str1=" + str1);
//
//          String str2 = "";
//
//          this.log
//              .trace(PREF_LOG_TRACE + "type == EntityType.HORSE ? " + (type == EntityType.HORSE));
//          this.log.trace(PREF_LOG_TRACE + "((HorseInventory) hInv).getArmor() != null ? "
//              + (((HorseInventory) horseInv).getArmor() != null));
//          this.log
//              .trace(PREF_LOG_TRACE + "type == EntityType.LLAMA ? " + (type == EntityType.LLAMA));
//          this.log.trace(PREF_LOG_TRACE + "type == EntityType.TRADER_LLAMA ? "
//              + (type == EntityType.TRADER_LLAMA));
//          if (horseInv instanceof LlamaInventory) {
//            this.log.trace(PREF_LOG_TRACE + "((LlamaInventory) hInv).getDecor() != null ? "
//                + (((LlamaInventory) horseInv).getDecor() != null));
//          }
//          if (type == EntityType.HORSE && ((HorseInventory) horseInv).getArmor() != null) {
//            horseData.putString("Armor",
//                ((HorseInventory) horseInv).getArmor().getType().toString());
//            str2 = "[" + ((HorseInventory) horseInv).getArmor().getType().toString() + "]";
//            this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//            this.log.trace(PREF_LOG_TRACE + "str2=" + str2);
//
//          } else if ((type == EntityType.LLAMA || type == EntityType.TRADER_LLAMA)
//              && ((LlamaInventory) horseInv).getDecor() != null) {
//            horseData.putString("Armor",
//                ((LlamaInventory) horseInv).getDecor().getType().toString());
//            str2 = "[" + ((LlamaInventory) horseInv).getDecor().getType().toString() + "]";
//            this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//            this.log.trace(PREF_LOG_TRACE + "str2=" + str2);
//          }
//
//          this.log.trace(PREF_LOG_TRACE + "entity instanceof ChestedHorse ? "
//              + (entity instanceof ChestedHorse));
//          if (entity instanceof ChestedHorse) {
//            horseData.putBoolean("Chest", ((ChestedHorse) horse).isCarryingChest());
//            this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//            this.log.trace(PREF_LOG_TRACE + "((ChestedHorse) horse).isCarryingChest() ? "
//                + (((ChestedHorse) horse).isCarryingChest()));
//            if (((ChestedHorse) horse).isCarryingChest()) {
//              // ラマがカーペットとチェスト両持ちできる
//              str2 = str2 + "[CHEST]";
//              this.log.trace(PREF_LOG_TRACE + "str2=" + str2);
//            }
//          } else {
//            horseData.putBoolean("Chest", false);
//            this.log.trace(PREF_LOG_TRACE + "Put to horseData:horseData=" + horseData.toString());
//
//          }
//
//          this.log.trace(
//              PREF_LOG_TRACE + "(str1 + str2).length() > 0 ? " + ((str1 + str2).length() > 0));
//          if ((str1 + str2).length() > 0) {
//            loreList.add(str1 + str2);
//            this.log.trace(PREF_LOG_TRACE + "Add to loreList:loreList=" + loreList.toString());
//          }
//
//          this.log.trace(PREF_LOG_TRACE + "[LOOP START]for (int i = 2; i < hInv.getSize(); i++)");
//          for (int i = 2; i < horseInv.getSize(); i++) {
//            // チェストと鎧?を除く
//            this.log.trace(PREF_LOG_TRACE + "hInv.getItem(" + i + ") == null ? "
//                + (horseInv.getItem(i) == null));
//            if (horseInv.getItem(i) == null) {
//              continue;
//            }
//            this.log.trace(
//                PREF_LOG_TRACE + "hInv.getItem(" + i + ")=" + horseInv.getItem(i).toString());
//            horse.getWorld().dropItem(loc, horseInv.getItem(i));
//            this.log
//                .trace(PREF_LOG_TRACE + "horse.getWorld().dropItem(loc, hInv.getItem(" + i + "))");
//          }
//          this.log.trace(PREF_LOG_TRACE + "[LOOP END]for (int i = 2; i < hInv.getSize(); i++)");
//        }

        // 構築したタグ情報をインベントリ保存する
        editor.set(eggData.getHorseEggTagDataMap());
        this.log.trace(PREF_LOG_TRACE + "HorseEggTagDataMap: " + eggData.getHorseEggTagDataMap());
        editor.load();
        this.log.trace(PREF_LOG_TRACE + "RTagEditor.load().");
//        tag.put("HorseEgg", horseData);
//        this.log.trace(PREF_LOG_TRACE + "Put to tag:tag=" + tag.toString());
//        stack.setTag(tag);
//        this.log.trace(PREF_LOG_TRACE + "stack.setTag(tag)");

//        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(horseegg);
//        this.log.trace(PREF_LOG_TRACE
//            + "net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(horseegg)");
//        horseegg = CraftItemStack.asBukkitCopy(stack);
//
//        this.log.trace(PREF_LOG_TRACE + "horseegg <- CraftItemStack.asBukkitCopy(stack)");
//        this.log.trace(PREF_LOG_TRACE + "horseegg=" + horseegg.toString());
//
        ItemMeta meta = horseegg.getItemMeta();
        // ItemMetaとれなかったら、そもそもおかしいので処理終了
        if (Objects.isNull(meta)) {
          this.log.info("horseEgg ItemMeta is NULL.End.");
          return;
        }
        this.log.trace(PREF_LOG_TRACE + "ItemMeta meta <- horseegg.getItemMeta()");
        meta.setDisplayName(eggData.getDisplayName());
//        meta.setDisplayName(horse.getCustomName());
        this.log.trace(PREF_LOG_TRACE + "meta.setDisplayName(eggData.getDisplayName())");
        meta.setLore(eggData.getLoreList());
//        meta.setLore(loreList);
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
