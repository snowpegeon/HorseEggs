package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class ZombieHorseEntityWriter extends  EntityWriter {
  public ZombieHorseEntityWriter(AbstractHorse absHorse, Logger log){
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if(!result) {
      return false;
    }

    return true;
  }
}
