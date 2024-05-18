/**
 *
 */
package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * ラマを捕獲するためのHorseEggの実体クラス.
 */
public class LlamaEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link LlamaEggData#LlamaEggData(AbstractHorse)}<br>
   *     リリース：{@link LlamaEggData#LlamaEggData(HashMap)}
   *     </p>
   */
  public LlamaEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public LlamaEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのアイテム
   */
  public LlamaEggData(HashMap<String, ?> metaData) {
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
  @Override
  public EntityType getEntityType() {
    return EntityType.LLAMA;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.LLAMA_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.LLAMA;
  }

}
