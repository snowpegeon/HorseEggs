/**
 * @author sho_5414
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * Egg data for Horse.
 */
public class HorseEggData extends EggDataBase {

  /**
   * Constructor.
   */
  public HorseEggData() {
    super();
  }

  @Override
  public EntityType getEntityType() {
    return EntityType.HORSE;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.HORSE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.HORSE;
  }

}
