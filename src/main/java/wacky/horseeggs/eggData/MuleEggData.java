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
public class MuleEggData extends EggDataBase {

  /**
   * コンストラクタ
   */
  public MuleEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public MuleEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのmeta情報
   */
  public MuleEggData(HashMap<String, ?> metaData){
    super(metaData);
  }

  @Override
  public EntityType getEntityType() {
    return EntityType.MULE;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.MULE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.MULE;
  }

}
