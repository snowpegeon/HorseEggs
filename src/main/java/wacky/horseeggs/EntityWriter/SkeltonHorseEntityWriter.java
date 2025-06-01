package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class SkeltonHorseEntityWriter extends EntityWriter {
  public SkeltonHorseEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }

    SkeletonHorse skeltonHorse = (SkeletonHorse) this.getAbsHorse();

    // サドルの設定
    ItemStack saddle = getSaddle(eggData.getIsSaddled());
    skeltonHorse.getInventory().setSaddle(saddle);

    // 骨馬はすぐに乗れるようにテイム済みを設定する
    skeltonHorse.setTamed(true);

    return true;
  }
}
