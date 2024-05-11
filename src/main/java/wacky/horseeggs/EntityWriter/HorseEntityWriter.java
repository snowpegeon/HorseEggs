package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;
import wacky.horseeggs.eggData.EggDataBase;

public class HorseEntityWriter extends  EntityWriter{
  public HorseEntityWriter(AbstractHorse absHorse){
    super(absHorse);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if(!result) {
      return false;
    }

    Horse horse = (Horse) this.getAbsHorse();
    horse.setColor(Color.valueOf(eggData.getColor()));
    horse.setStyle(Style.valueOf(eggData.getStyle()));
    ItemStack armor = getArmor(eggData.getArmor());
    horse.getInventory().setArmor(armor);

    return true;
  }

  private ItemStack getArmor(String type){
    Material armor = null;
    armor = Material.matchMaterial(type, true);
    if(Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }

    armor = Material.matchMaterial(type);
    if(Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }
    return null;
  }
}
