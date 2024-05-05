/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * 
 */
public class ZombieHorseEggData extends EggDataBase {

  /**
   * 
   */
  public ZombieHorseEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public EntityType getEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return EntityType.ZOMBIE_HORSE;
  }

  @Override
  public Material getFilledEggMaterial() {
    // TODO 自動生成されたメソッド・スタブ
    return Material.ZOMBIE_HORSE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}
