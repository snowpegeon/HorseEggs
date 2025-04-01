package wacky.horseeggs.EntityWriter;

import java.util.Objects;
import java.util.stream.Stream;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import com.github.teruteru128.logger.Logger;
import wacky.horseeggs.eggData.EggDataBase;

public class HorseEntityWriter extends EntityWriter {
  public HorseEntityWriter(AbstractHorse absHorse, Logger log) {
    super(absHorse, log);
  }

  public boolean writeHorse(EggDataBase eggData) {
    boolean result = writeHorseBase(eggData);
    if (!result) {
      return false;
    }

    Horse horse = (Horse) this.getAbsHorse();

    // 色の設定
    horse.setColor(Color.valueOf(eggData.getColor()));

    // スタイルの設定
    horse.setStyle(Style.valueOf(eggData.getStyle()));

    // 鎧の設定
    ItemStack armor = getArmor(eggData.getArmor());
    if (Objects.nonNull(armor) && armor.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta
        && Objects.nonNull(eggData.getArmorColor())) {
      int[] colors =
          Stream.of(eggData.getArmorColor().split(",")).mapToInt(Integer::parseInt).toArray();
      leatherArmorMeta
          .setColor(org.bukkit.Color.fromARGB(colors[0], colors[1], colors[2], colors[3]));
      armor.setItemMeta(leatherArmorMeta);
    }
    horse.getInventory().setArmor(armor);

    // サドルの設定
    ItemStack saddle = getSaddle(eggData.getIsSaddled());
    horse.getInventory().setSaddle(saddle);

    return true;
  }
}
