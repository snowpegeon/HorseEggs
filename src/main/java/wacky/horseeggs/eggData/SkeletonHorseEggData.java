/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 *
 */
public class SkeletonHorseEggData extends EggDataBase {

  /**
   * 
   */
  public SkeletonHorseEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public EntityType getEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return EntityType.SKELETON_HORSE;
  }

  @Override
  public Material getFilledEggMaterial() {
    // TODO 自動生成されたメソッド・スタブ
    return Material.SKELETON_HORSE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}
