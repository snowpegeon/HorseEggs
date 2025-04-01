package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class DonkyEntityWriter extends EntityWriter {
  public DonkyEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }

    Donkey donkey = (Donkey) this.getAbsHorse();

    // サドルの設定
    ItemStack saddle = getSaddle(eggData.getIsSaddled());
    this.getAbsHorse().getInventory().setSaddle(saddle);

    // チェスト付きの設定
    donkey.setCarryingChest(eggData.getIsCarryingChest());

    return true;
  }
}
