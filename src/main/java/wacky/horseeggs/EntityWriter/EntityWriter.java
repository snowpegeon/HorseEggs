package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * Base class Writing for HorseEgg AbstractHorse.
 */
@Getter
public abstract class EntityWriter {
  /**
   * <p>
   * ログのプレフィックス(TRACE). {@link String}
   * </p>
   */
  protected static final String PREF_LOG_TRACE = "[TRACE] ";

  /**
   * <p>
   * ログのプレフィックス(DEBUG). {@link String}
   * </p>
   */
  protected static final String PREF_LOG_DEBUG = "[DEBUG] ";
  
  /**
   * <p>
   * ログの開始プレフィックス. {@link String}
   * </p>
   */
  protected static final String PREF_LOG_START = "[START] ";

  /**
   * <p>
   * ログの終了プレフィックス. {@link String}
   * </p>
   */
  protected static final String PREF_LOG_END = "[END] ";

  private AbstractHorse absHorse;

  /** ロガー. */
  private Logger logger;

  public EntityWriter(AbstractHorse abh, Logger log){
    this.absHorse = abh;
    this.logger = log;
  }

  public abstract boolean writeHorse(EggDataBase eggData);

  protected boolean writeHorseBase(EggDataBase eggData) {
    this.logger.debug(PREF_LOG_DEBUG + PREF_LOG_START + "writeHorseBase(EggDataBase)");
    this.logger.debug(PREF_LOG_DEBUG + "[IN PARAM]eggData=" + eggData.toString());

    this.absHorse.setAge(6000); // 繁殖待ち6000tick
    this.absHorse.setCustomName(eggData.getName());
    this.absHorse.setMaxHealth(eggData.getMaxHealth());
    this.absHorse.setHealth(eggData.getHealth());
    this.absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(eggData.getSpeed());
    this.absHorse.setJumpStrength(eggData.getJump());

    OfflinePlayer owner = getPlayer(eggData.getUuidMost(), eggData.getUuidLeast());
    this.absHorse.setOwner(owner);

//    ItemStack saddle = getSaddle(eggData.getIsSaddled());
//    this.absHorse.getInventory().setSaddle(saddle);

    // チェスト付きの設定
//    if (this.absHorse instanceof ChestedHorse) {
//      ChestedHorse cHorse = (ChestedHorse) this.absHorse;
//      cHorse.setCarryingChest(eggData.getIsCarryingChest());
//    }

    this.logger.debug(PREF_LOG_DEBUG + "[RETURN PARAM]true");
    this.logger.debug(PREF_LOG_DEBUG + PREF_LOG_END + "writeHorseBase(EggDataBase)");
    return true;
  }

  private OfflinePlayer getPlayer(Long most, Long least) {
    if(Objects.nonNull(most) && Objects.nonNull(least)){
      return Bukkit.getOfflinePlayer(new UUID(most, least));
    }
    return null;
  }

  protected ItemStack getSaddle(boolean isSaddled) {
    ItemStack saddle = null;
    if(isSaddled){
      saddle = new ItemStack(Material.SADDLE, 1);
    }
    return saddle;
  }

  protected ItemStack getArmor(String type) {
    Material armor = null;

    if (Objects.isNull(type)) {
      return null;
    }

    armor = Material.matchMaterial(type);
    if (Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }

    armor = Material.matchMaterial(type, true);
    if (Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }
    return null;
  }
  
  protected ItemStack getDecor(String armorStr){
    ItemStack armor = null;
    if(Objects.nonNull(armorStr)){
      armor = new ItemStack(Material.getMaterial(armorStr));
    }

    return armor;
  }
}
