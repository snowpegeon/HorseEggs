package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Mule;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class MuleEntityWriter extends EntityWriter {
  public MuleEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }

    Mule mule = (Mule) this.getAbsHorse();

    // サドルの設定
    ItemStack saddle = getSaddle(eggData.getIsSaddled());
    mule.getInventory().setSaddle(saddle);

    // チェスト付きの設定
    mule.setCarryingChest(eggData.getIsCarryingChest());

    return true;
  }
}
