package wacky.horseeggs.minecraftIO;

import com.github.teruteru128.logger.Logger;
import com.saicone.rtag.RtagEntity;
import com.saicone.rtag.RtagItem;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
// import net.minecraft.nbt.CompoundTag;
// import net.minecraft.nbt.ListTag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
// import org.bukkit.craftbukkit.v1_20_R3.entity.CraftAbstractHorse;
// import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;
import wacky.horseeggs.EntityWriter.EntityWriter;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * 馬リリース処理の実装クラス.
 * 
 */
public class ReleaseHorse {
  /** ログのプレフィックス(TRACE). */
  private static final String PREF_LOG_TRACE = "[TRACE] ";
  /** ログのプレフィックス(DEBUG). */
  private static final String PREF_LOG_DEBUG = "[DEBUG] ";
  /** . */
  private static final String HORSE_EGG_KEY = "HorseEgg";
  /** ロガー. */
  private Logger log;

  /**
   * コンストラクタ.
   *
   * @param item {@link org.bukkit.inventory.ItemStack}
   * @param loc {@link org.bukkit.Location}
   * @param logger {@link org.bukkit.Location}
   * 
   */
  public ReleaseHorse(ItemStack item, Location loc, Logger logger) {
    this.log = logger;
    this.log.debug(PREF_LOG_DEBUG + "ReleaseHorse.ReleaseHorse(ItemStack, Location, Logger):Start");
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]item=" + item.toString());
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]loc=" + loc.toString());
    this.log.trace(PREF_LOG_TRACE + "[IN PARAM]logger=" + logger.toString());

    this.log.trace(PREF_LOG_TRACE + "releseHorseEgg(item, loc)");
//    boolean releseResult = releseHorseFromHorseEgg(item, loc);
    this.log.trace(PREF_LOG_TRACE + "boolean releseResult <- releseHorseFromHorseEgg(item, loc)");
//    this.log.trace(PREF_LOG_TRACE + "releseResult=" + releseResult);
//    if (releseResult) {
//      this.log.debug(PREF_LOG_DEBUG + "ReleaseHorse.ReleaseHorse(ItemStack, Location, Logger):End");
//      return;
//    }

//    this.log.trace(PREF_LOG_TRACE + "--- Run old implements ---");
//    String name = null;
//    Double MaxHP = 0.0;
//    Double HP = 0.0;
//    Double speed = 0.0;
//    Double jump = 0.0;
//    OfflinePlayer owner = null;
//    ItemStack saddle = null;
//    ItemStack armor = null;
//    Boolean chest = false;
//    Variant variant = null; // もはや別Entity.
//    EntityType type = null;
//    Color color = null;
//    Llama.Color Lcolor = null;
//    Style style = null;
//    short decor = 0;
//    int strength = 0;

//    net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
//    CompoundTag horseData = stack.getTag().getCompound("HorseEgg");
////    CompoundTag horseData = new CompoundTag();
//    if (!horseData.isEmpty()) { // NBTから読むだけ簡単
//      log.debug("horseData is not Empty");
//      name = horseData.getString("Name");
//      MaxHP = horseData.getDouble("MaxHealth");
//      HP = horseData.getDouble("Health");
//      speed = horseData.getDouble("Speed");
//      jump = horseData.getDouble("Jump");
//      log.trace("horseData.getString(\"Name\"):" + name);
//      log.trace("horseData.getDouble(\"MaxHealth\"):" + MaxHP);
//      log.trace("horseData.getDouble(\"Health\"):" + HP);
//      log.trace("horseData.getDouble(\"Speed\"):" + speed);
//      log.trace("horseData.getDouble(\"Jump\"):" + jump);

//      if (horseData.getLong("UUIDMost") != 0) {
//        log.trace("horseData.getLong(\"UUIDMost\") != 0");
//        owner = Bukkit.getOfflinePlayer(
//            new UUID(horseData.getLong("UUIDMost"), horseData.getLong("UUIDLeast")));
//      }
//
//      if (horseData.getBoolean("Saddle")) {
//        log.trace("has Saddle");
//        saddle = new ItemStack(Material.SADDLE);
//      }
//      if (!horseData.getString("Armor").isEmpty()) { // 馬鎧またはラマのカーペット
//        log.trace("has Armor");
//        decor = horseData.getShort("Decor"); // 1.12までの仕様
//        armor = new ItemStack(Material.getMaterial(horseData.getString("Armor")), 1, decor);
//      }
//      chest = horseData.getBoolean("Chest");
//      log.trace("horseData.getBoolean(\"Chest\"):" + chest);
//
//      if (!horseData.getString("Type").isEmpty()) { // 旧バージョンだとなかったりする。
//        log.trace("!horseData.getString(\"Type\").isEmpty()");
//        type = EntityType.valueOf(horseData.getString("Type"));
//      } else {
//        log.trace("horseData.getString(\"Type\").isEmpty()");
//        variant = Variant.valueOf(horseData.getString("Variant"));
//        log.trace("Variant.valueOf(horseData.getString(\"Variant\"):" + variant);
//        switch (variant) {
//          case HORSE:
//            type = EntityType.HORSE;
//            break;
//          case DONKEY:
//            type = EntityType.DONKEY;
//            break;
//          case MULE:
//            type = EntityType.MULE;
//            break;
//          case UNDEAD_HORSE:
//            type = EntityType.ZOMBIE_HORSE;
//            break;
//          case SKELETON_HORSE:
//            type = EntityType.SKELETON_HORSE;
//            break;
//          case LLAMA:
//            type = EntityType.LLAMA;
//            break;
//          default:
//            type = EntityType.HORSE;
//        }
//      }
//      log.trace("entity type:" + type);
//
//      if (type == EntityType.HORSE) {
//        log.trace("type is HORSE");
//        color = Color.valueOf(horseData.getString("Color"));
//        style = Style.valueOf(horseData.getString("Style"));
//      } else if (type == EntityType.LLAMA || type == EntityType.TRADER_LLAMA) {
//        log.trace("type is LLAMA OR TRADER_LLAMA");
//        Lcolor = Llama.Color.valueOf(horseData.getString("Color"));
//        strength = horseData.getInt("Strength");
//      }
////    } else { // Loreから読み取り
//      log.debug("horseData is Empty");
//
//      name = item.getItemMeta().getDisplayName();
//      log.trace("item.getItemMeta().getDisplayName():" + name);
//      List<String> list = item.getItemMeta().getLore();
//      for (int i = 0; i < list.size(); i++) { // 順番とか気にしなくて良くなる
//        String str = list.get(i);
//        log.trace("item.getItemMeta().getLore():" + str);
//
//        if (str.startsWith("HP")) {
//          MaxHP = NumberConversions.toDouble(str.split(" ")[1].split("/")[1]);
//          HP = NumberConversions.toDouble(str.split(" ")[1].split("/")[0]);
//          log.trace("MaxHP:" + MaxHP);
//          log.trace("HP:" + HP);
//        } else if (str.startsWith("Speed")) {
//          speed = NumberConversions.toDouble(str.split(" ")[1]) / 43;
//          log.trace("Speed:" + speed);
//
//        } else if (str.startsWith("Jump")) {
//          jump = NumberConversions.toDouble(str.split(" ")[1]);
//          log.trace("jump:" + jump);
//
//        } else if (str.startsWith("Height")) { // 空白であってます
//          log.trace("Height(str):" + str);
//
//        } else if (str.startsWith("Owner")) {
//          owner = Bukkit.getOfflinePlayer(str.split(" ")[1]);
//          log.trace("jump:" + owner);
//
//        } else if (str.startsWith("[")) { // 装備は必ず[]で囲む
//          if (str.contains("SADDLE")) {
//            log.trace("str has SADDLE");
//            saddle = new ItemStack(Material.SADDLE);
//          }
//          if (str.contains("IRON_HORSE_ARMOR") || str.contains("IRON_BARDING")) {
//            log.trace("str has IRON_HORSE_ARMOR OR IRON_BARDING");
//            armor = new ItemStack(Material.IRON_HORSE_ARMOR);
//          } else if (str.contains("GOLDEN_HORSE_ARMOR") || str.contains("GOLD_BARDING")) {
//            log.trace("str has GOLDEN_HORSE_ARMOR OR GOLD_BARDING");
//            armor = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
//          } else if (str.contains("DIAMOND_HORSE_ARMOR") || str.contains("DIAMOND_BARDING")) {
//            log.trace("str has DIAMOND_HORSE_ARMOR OR DIAMOND_BARDING");
//            armor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
//          }
//          chest = str.contains("CHEST");
//          log.trace("str has CHEST:" + chest);
//
//        } else { // 消去法 残ったのは馬の種類、色、模様
//          log.trace("str.contains(\"/\"):" + str.contains("/"));
//          if (str.contains("/")) {
//            variant = Variant.HORSE;
//            color = Color.valueOf(str.split("/")[0]);
//            style = Style.valueOf(str.split("/")[1]);
//            log.trace("variant:" + variant);
//            log.trace("color:" + color);
//            log.trace("style:" + style);
//          } else {
//            variant = Variant.valueOf(str);
//            log.trace("variant:" + variant);
//          }
//        }
//      }

//    }

//     speedは書き込みもめんｄ
//    CompoundTag tag = new CompoundTag();
//    net.minecraft.world.entity.animal.horse.AbstractHorse eh =
//        ((CraftAbstractHorse) horse).getHandle();

    // 一旦普通の馬のNBTコピー
//    eh.addAdditionalSaveData(tag);
//    ListTag attributes = tag.getList("Attributes", 10);
//    for (int j = 0; j < attributes.size(); j++) {
//      CompoundTag attr = (CompoundTag) attributes.get(j);
//      log.trace("attr.getString(\"Name\"):" + attr.getString("Name"));
//      if (attr.getString("Name").equals("minecraft:generic.movement_speed")) {
//        attr.putDouble("Base", speed);
//        attributes.set(j, attr);
//        break;
//      }
//    }
//    tag.put("Attributes", attributes);
//    eh.readAdditionalSaveData(tag); // 速度書き込んでペースト
//    horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(eggData.getSpeed());
//
//    horse.setAge(6000); // 繁殖待ち6000tick
//    horse.setCustomName(name);
//    horse.setMaxHealth(MaxHP);
//    horse.setHealth(HP);
//
//    horse.setJumpStrength(jump);
//    horse.setOwner(owner);
//    horse.getInventory().setSaddle(saddle);
//    if (horse.getType() == EntityType.HORSE) {
//      log.trace("horse.getType() == EntityType.HORSE");
//      ((Horse) horse).setColor(color);
//      ((Horse) horse).setStyle(style);
//      ((Horse) horse).getInventory().setArmor(armor);
//    } else if (horse.getType() == EntityType.SKELETON_HORSE) {
//      // 骨馬は常時乗れるように
//      log.trace("horse.getType() == EntityType.SKELETON_HORSE");
//      horse.setTamed(true);
//
//    } else if (horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA) {
//      // 凝ってるなぁorz
//      log.trace(
//          "horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA");
//      ((Llama) horse).setColor(Lcolor);
//      ((Llama) horse).setStrength(strength);
//      ((Llama) horse).getInventory().setDecor(armor);
//      ((ChestedHorse) horse).setCarryingChest(chest);
//    } else if (horse instanceof ChestedHorse) {
//      log.trace("horse instanceof ChestedHorse");
//      ((ChestedHorse) horse).setCarryingChest(chest);
//    }
  }

  public static boolean releseHorseFromHorseEgg(ItemStack item, Location loc, Logger logger) {
    logger
        .debug(PREF_LOG_DEBUG + "ReleaseHorse.boolean:releseHorseEgg(ItemStack, Location):Start");
    logger.trace(PREF_LOG_TRACE + "[IN PARAM]item=" + item.toString());
    logger.trace(PREF_LOG_TRACE + "[IN PARAM]loc=" + loc.toString());

    // ItemStackは何らかの理由で変質することがあるので、コピーして利用
    ItemStack stack = item.clone();

    // データの読み取り処理
    logger.trace(PREF_LOG_TRACE + "RtagItem rtagItem <- new RtagItem(item)");
    RtagItem tagItem = new RtagItem(stack);
    logger.trace(PREF_LOG_TRACE + tagItem.toString());

    logger.trace(PREF_LOG_TRACE + "var horseEggKey <- rtagItem.get(HORSE_EGG_KEY)");
    HashMap<String, Object> he = tagItem.get(HORSE_EGG_KEY);
    if (he != null) {
      logger.trace(PREF_LOG_TRACE + he.toString());
    }
    logger.trace(PREF_LOG_TRACE + "[IN PARAM]he=" + he);

    logger.trace(PREF_LOG_TRACE + "var eggData <- new EggDataFactory().newEggData(stack.getType(), he)");
    EggDataBase eggData = new EggDataFactory().newEggData(stack.getType(), he);
    if(Objects.isNull(eggData)){
      logger.error("This MaterialType is null.");
      return false;
    }

    // 馬生成をギリギリまで遅らせる 1.12では馬、ロバ、骨馬は全て別種
    AbstractHorse horse = (AbstractHorse) loc.getWorld().spawnEntity(loc, eggData.getEntityType());

    // 馬情報の書き込み
    EntityWriter eWriter = EntityWriterFactory.newLoreWriter(eggData.getEntityType(), horse);
    eWriter.writeHorse(eggData);
    logger.trace(PREF_LOG_TRACE + "[RETURN PARAM]true");
    logger.debug(PREF_LOG_DEBUG + "ReleaseHorse.boolean:releseHorseEgg(ItemStack, Location):End");
    return true;
  }
  
  private boolean captureHorseToHorseEgg() {
    
    return true;
  }
}
