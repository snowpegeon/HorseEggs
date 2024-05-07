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
   * @param item HorseEggsのアイテム
   */
  public MuleEggData(ItemStack item){
    super(item);
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
