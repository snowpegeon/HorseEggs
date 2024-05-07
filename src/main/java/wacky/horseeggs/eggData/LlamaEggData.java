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
public class LlamaEggData extends EggDataBase {

  /**
   * コンストラクタ
   */
  public LlamaEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public LlamaEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param item HorseEggsのアイテム
   */
  public LlamaEggData(ItemStack item){
    super(item);
  }

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
