package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;
import wacky.horseeggs.eggData.EggDataBase;

public class DonkyEntityWriter extends  EntityWriter {
  public DonkyEntityWriter(AbstractHorse absHorse){
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
