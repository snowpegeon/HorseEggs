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
 * 
 */
public class TraderLlamaEggData extends LlamaEggData {

  /**
   * コンストラクタ
   */
  public TraderLlamaEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
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
