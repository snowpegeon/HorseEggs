package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * <p>
 * ゾンビホースを捕獲するためのHorseEggの実体クラス.
 * </p>
 */
public class ZombieHorseEggData extends EggDataBase {

  /**
   * <p>
   * デフォルトコンストラクタ.
   * </p>
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
   * <p>
   * コンストラクタ（AbstractHorse） .
   * </p>
   *
   * @param absHorse スポーン中の馬情報.
   */
  public ZombieHorseEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * <p>
   * コンストラクタ（ItemStack）.
   * </p>
   *
   * @param metaData HorseEggsのmeta情報
   */
  public ZombieHorseEggData(HashMap<String, ?> metaData) {
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
