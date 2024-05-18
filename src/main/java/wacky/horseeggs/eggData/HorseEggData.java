/**
 * @author sho_5414
 */
package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * 馬を捕獲するためのHorseEggの実体クラス.
 */
public class HorseEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link HorseEggData#HorseEggData(AbstractHorse)}<br>
   *     リリース：{@link HorseEggData#HorseEggData(HashMap)}
   *     </p>
   */
  public HorseEggData() {
    super();
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public HorseEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのアイテム
   */
  public HorseEggData(HashMap<String, ?> metaData){
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
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
