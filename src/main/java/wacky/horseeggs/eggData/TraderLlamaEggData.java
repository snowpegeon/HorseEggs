package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * <p>
 * 商人ラマを捕獲するためのHorseEggの実体クラス.
 * </p>
 */
public class TraderLlamaEggData extends LlamaEggData {

  /**
   * <p>
   * デフォルトコンストラクタ.
   * </p>
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link TraderLlamaEggData#TraderLlamaEggData(AbstractHorse)}<br>
   *     リリース：{@link TraderLlamaEggData#TraderLlamaEggData(HashMap)}
   *     </p>
   */
  public TraderLlamaEggData() {
    super();
  }

  /**
   * <p>
   * コンストラクタ（AbstractHorse） .
   * </p>
   *
   * @param absHorse スポーン中の馬情報.
   */
  public TraderLlamaEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * <p>
   * コンストラクタ（ItemStack）.
   * </p>
   *
   * @param metaData HorseEggsのmeta情報
   */
  public TraderLlamaEggData(HashMap<String, ?> metaData) {
    super(metaData);
  }

  /**
   * <p>
   * 卵に格納できるエンティティタイプを取得.
   * </p>
   *
   * @return {@link EntityType}.
   */
  @Override
  public EntityType getEntityType() {
    return EntityType.TRADER_LLAMA;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.TRADER_LLAMA_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.TRADER_LLAMA;
  }

}
