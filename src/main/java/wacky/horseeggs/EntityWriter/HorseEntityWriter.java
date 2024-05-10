package wacky.horseeggs.EntityWriter;

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
    ItemStack armor = null;
      if (type.contains("IRON_HORSE_ARMOR") || type.contains("IRON_BARDING")) {
        armor = new ItemStack(Material.IRON_HORSE_ARMOR);
      } else if (type.contains("GOLDEN_HORSE_ARMOR") || type.contains("GOLD_BARDING")) {
        armor = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
      } else if (type.contains("DIAMOND_HORSE_ARMOR") || type.contains("DIAMOND_BARDING")) {
        armor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
      }
      return armor;
  }
}
