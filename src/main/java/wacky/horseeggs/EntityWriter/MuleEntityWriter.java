package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import wacky.horseeggs.eggData.EggDataBase;

public class MuleEntityWriter extends  EntityWriter {
  public MuleEntityWriter(AbstractHorse absHorse){
    super(absHorse);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if(!result) {
      return false;
    }

    return true;
  }
}
