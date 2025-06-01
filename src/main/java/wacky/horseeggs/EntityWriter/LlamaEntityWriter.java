package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Llama.Color;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class LlamaEntityWriter extends EntityWriter {
  public LlamaEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }
    Llama llama = (Llama) this.getAbsHorse();

    // 色の設定
    llama.setColor(Color.valueOf(eggData.getColor()));
    
    // インベントリスロットの設定
    llama.setStrength(eggData.getStrength());

    // 装飾の設定
    ItemStack decor = getDecor(eggData.getArmor());
    llama.getInventory().setDecor(decor);

    // チェスト付きの設定
    llama.setCarryingChest(eggData.getIsCarryingChest());

    return true;
  }
}
