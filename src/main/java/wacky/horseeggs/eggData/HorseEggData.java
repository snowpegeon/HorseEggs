/**
 * @author sho_5414
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * Egg data for Horse.
 */
public class HorseEggData extends EggDataBase {

  /**
   * コンストラクタ.
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
   * @param item HorseEggsのアイテム
   */
  public HorseEggData(ItemStack item){
    super(item);
  }

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
