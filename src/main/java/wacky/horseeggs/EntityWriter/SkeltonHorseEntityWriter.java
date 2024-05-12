package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import wacky.horseeggs.eggData.EggDataBase;

public class SkeltonHorseEntityWriter extends  EntityWriter {
  public SkeltonHorseEntityWriter(AbstractHorse absHorse){
    super(absHorse);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if(!result) {
      return false;
    }

    // 骨馬はすぐに乗れるようにテイム済みを設定する
    this.getAbsHorse().setTamed(true);

    return true;
  }
}
