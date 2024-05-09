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
public class ZombieHorseEggData extends EggDataBase {

  /**
   * コンストラクタ
   */
  public ZombieHorseEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public ZombieHorseEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのmeta情報
   */
  public ZombieHorseEggData(HashMap<String, ?> metaData){
    super(metaData);
  }

  @Override
  public EntityType getEntityType() {
    return EntityType.ZOMBIE_HORSE;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.ZOMBIE_HORSE_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.ZOMBIE_HORSE;
  }

}
