/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * 
 */
public class TraderLlamaEggData extends EggDataBase {

  /**
   * 
   */
  public TraderLlamaEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public EntityType getEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return EntityType.TRADER_LLAMA;
  }

  @Override
  public Material getFilledEggMaterial() {
    // TODO 自動生成されたメソッド・スタブ
    return Material.TRADER_LLAMA_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    // TODO 自動生成されたメソッド・スタブ
    return null;
  }

}
