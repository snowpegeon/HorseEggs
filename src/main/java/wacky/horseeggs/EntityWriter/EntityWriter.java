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
import org.bukkit.inventory.ItemStack;
import wacky.horseeggs.EntityWriter.factory.EntityWriterFactory;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * Base class Writing for HorseEgg AbstractHorse.
 */
@Getter
public abstract class EntityWriter {
  private AbstractHorse absHorse;
  public EntityWriter(AbstractHorse abh){
    this.absHorse = abh;
  }

  public abstract boolean writeHorse(EggDataBase eggData);

  protected boolean writeHorseBase(EggDataBase eggData){
    this.absHorse.setAge(6000); // 繁殖待ち6000tick
    this.absHorse.setCustomName(eggData.getName());
    this.absHorse.setMaxHealth(eggData.getMaxHealth());
    this.absHorse.setHealth(eggData.getHealth());
    this.absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(eggData.getSpeed());

    this.absHorse.setJumpStrength(eggData.getJump());

    OfflinePlayer owner = getPlayer(eggData.getUuidMost(), eggData.getUuidLeast());
    this.absHorse.setOwner(owner);

    ItemStack saddle = getSaddle(eggData.getIsSaddled());
    this.absHorse.getInventory().setSaddle(saddle);

    // チェスト付きの設定
    if(this.absHorse instanceof ChestedHorse){
      ChestedHorse cHorse = (ChestedHorse) this.absHorse;
      cHorse.setCarryingChest(eggData.getIsCarryingChest());
    }
    return true;
  }

  private OfflinePlayer getPlayer(Long most, Long least) {
    if(Objects.nonNull(most) && Objects.nonNull(least)){
      return Bukkit.getOfflinePlayer(new UUID(most, least));
    }
    return null;
  }

  private ItemStack getSaddle(boolean isSaddled) {
    ItemStack saddle = null;
    if(isSaddled){
      saddle = new ItemStack(Material.SADDLE);
    }
    return saddle;
  }
}
