package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import java.util.stream.Stream;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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
    if (Objects.nonNull(armor) && armor.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta && Objects.nonNull(
        eggData.getArmorColor())) {
      int[] colors = Stream.of(eggData.getArmorColor().split(",")).mapToInt(Integer::parseInt)
          .toArray();
      leatherArmorMeta.setColor(
          org.bukkit.Color.fromARGB(colors[0], colors[1], colors[2], colors[3]));
      armor.setItemMeta(leatherArmorMeta);
    }
    horse.getInventory().setArmor(armor);

    return true;
  }

  private ItemStack getArmor(String type){
    Material armor = null;

    if(Objects.isNull(type)) {
      return null;
    }

    armor = Material.matchMaterial(type);
    if(Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }

    armor = Material.matchMaterial(type, true);
    if(Objects.nonNull(armor)) {
      return new ItemStack(armor);
    }
    return null;
  }
}
