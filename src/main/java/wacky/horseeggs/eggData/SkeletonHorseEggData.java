/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 *
 */
public class SkeletonHorseEggData extends EggDataBase {

  /**
   * コンストラクタ
   */
  public SkeletonHorseEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public SkeletonHorseEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param item HorseEggsのアイテム
   */
  public SkeletonHorseEggData(ItemStack item){
    super(item);
  }

  @Override
  public EntityType getEntityType() {
    return EntityType.SKELETON_HORSE;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.SKELETON_HORSE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.SKELETON_HORSE;
  }

}
