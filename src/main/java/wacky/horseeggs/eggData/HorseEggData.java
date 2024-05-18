package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * 馬を捕獲するためのHorseEggの実体クラス.
 */
public class HorseEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link LlamaEggData#LlamaEggData(AbstractHorse)}<br>
   *     リリース：{@link LlamaEggData#LlamaEggData(HashMap)}
   *     </p>
   */
  public HorseEggData() {
    super();
  }

  /**
   * <p>
   * コンストラクタ（AbstractHorse） .
   * </p>
   *
   * @param absHorse スポーン中の馬情報.
   */
  public HorseEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * <p>
   * コンストラクタ（ItemStack）.
   * </p>
   *
   * @param metaData HorseEggsのmeta情報
   */
  public HorseEggData(HashMap<String, ?> metaData) {
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
