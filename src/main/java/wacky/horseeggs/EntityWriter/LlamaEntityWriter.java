package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Llama.Color;
import org.bukkit.inventory.ItemStack;
import wacky.horseeggs.eggData.EggDataBase;

public class LlamaEntityWriter extends  EntityWriter {
  public LlamaEntityWriter(AbstractHorse absHorse){
    super(absHorse);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if(!result) {
      return false;
    }
    Llama llama = (Llama) this.getAbsHorse();

    llama.setColor(Color.valueOf(eggData.getColor()));
    llama.setStrength(eggData.getStrength());

    ItemStack armor = getArmor(eggData.getArmor());
    llama.getInventory().setDecor(armor);

    return true;
  }

  private ItemStack getArmor(String armorStr){
    ItemStack armor = null;
    if(Objects.nonNull(armorStr)){
      armor = new ItemStack(Material.getMaterial(armorStr));
    }

    return armor;
  }
}
