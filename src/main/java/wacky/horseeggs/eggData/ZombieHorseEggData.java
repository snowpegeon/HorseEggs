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
 * ゾンビホースを捕獲するためのHorseEggの実体クラス.
 */
public class ZombieHorseEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link ZombieHorseEggData#ZombieHorseEggData(AbstractHorse)}<br>
   *     リリース：{@link ZombieHorseEggData#ZombieHorseEggData(HashMap)}
   *     </p>
   */
  public ZombieHorseEggData() {
    super();
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

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
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
