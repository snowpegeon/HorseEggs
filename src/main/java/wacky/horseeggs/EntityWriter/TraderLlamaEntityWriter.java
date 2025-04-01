package wacky.horseeggs.EntityWriter;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Llama.Color;
import org.bukkit.entity.TraderLlama;
import org.bukkit.inventory.ItemStack;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class TraderLlamaEntityWriter extends EntityWriter {
  public TraderLlamaEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }
    TraderLlama traderLlama = (TraderLlama) this.getAbsHorse();

    // 色の設定
    traderLlama.setColor(Color.valueOf(eggData.getColor()));
    
    // インベントリスロットの設定
    traderLlama.setStrength(eggData.getStrength());

    // 装飾の設定
    ItemStack decor = getDecor(eggData.getArmor());
    traderLlama.getInventory().setDecor(decor);

    // チェスト付きの設定
    traderLlama.setCarryingChest(eggData.getIsCarryingChest());

    return true;
  }
}
