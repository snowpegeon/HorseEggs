/**
 *
 */
package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * スケルトンホースを捕獲するためのHorseEggの実体クラス.
 */
public class SkeletonHorseEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link SkeletonHorseEggData#SkeletonHorseEggData(AbstractHorse)}<br>
   *     リリース：{@link SkeletonHorseEggData#SkeletonHorseEggData(HashMap)}
   *     </p>
   */
  public SkeletonHorseEggData() {
    super();
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public SkeletonHorseEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのmeta情報
   */
  public SkeletonHorseEggData(HashMap<String, ?> metaData) {
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
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
