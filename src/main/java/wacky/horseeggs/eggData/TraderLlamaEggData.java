/**
 * 
 */
package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * 商人ラマを捕獲するためのHorseEggの実体クラス.
 */
public class TraderLlamaEggData extends LlamaEggData {

  /**
   * デフォルトコンストラクタ.
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
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public TraderLlamaEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのmeta情報
   */
  public TraderLlamaEggData(HashMap<String, ?> metaData){
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
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
