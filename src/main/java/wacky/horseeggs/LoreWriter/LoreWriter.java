/**
 * 
 */
package wacky.horseeggs.LoreWriter;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;

/**
 * Base class Writing for HorseEgg ItemStack Lore.
 */
public abstract class LoreWriter {
  public abstract List<String> generateLore(Entity entity);

  protected String getHealthMeter(double currentHealth, double maxHealth) {
    double rate = currentHealth * 20 / maxHealth;

    int i = 0;
    StringBuilder healthMeterSb = new StringBuilder();
    healthMeterSb.append("Health:[");
    healthMeterSb.append(ChatColor.GREEN);
    healthMeterSb.append("");
    for (; i < rate; i++) {
      healthMeterSb.append(":");
    }
    healthMeterSb.append(ChatColor.GRAY);
    healthMeterSb.append("");
    for (; i < 20; i++) {
      healthMeterSb.append(":");
    }
    healthMeterSb.append(ChatColor.DARK_PURPLE);
    healthMeterSb.append("](");
    healthMeterSb.append(currentHealth);
    healthMeterSb.append(" / ");
    healthMeterSb.append(maxHealth);
    healthMeterSb.append(")");

    return healthMeterSb.toString();
  }
}
