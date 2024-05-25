package wacky.horseeggs;

import com.github.teruteru128.logger.Logger;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;
import wacky.horseeggs.minecraftIO.ReleaseHorse;


// うまく行ってないので凍結中
public class ItemDespawnListener implements Listener {

  private HorseEggs plugin;
  private Logger logger;

  public ItemDespawnListener(HorseEggs plugin, Logger log) {
    this.plugin = plugin;
    this.logger = log;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler //
  public void onItemDespawn(ItemDespawnEvent event) {
    ItemStack item = event.getEntity().getItemStack();
    if (this.plugin.isHorseEgg(item)) {
      Location loc = event.getLocation();
      new ReleaseHorse(item, loc, this.logger);
    }
  }
}
