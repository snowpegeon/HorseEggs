package wacky.horseeggs.eggData;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;

/**
 * ラバを捕獲するためのHorseEggの実体クラス.
 */
public class MuleEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br>
   *     使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link MuleEggData#MuleEggData(AbstractHorse)}<br>
   *     リリース：{@link MuleEggData#MuleEggData(HashMap)}
   *     </p>
   */
  public MuleEggData() {
    super();
  }

  /**
   * <p>
   * コンストラクタ（AbstractHorse） .
   * </p>
   *
   * @param absHorse スポーン中の馬情報.
   */
  public MuleEggData(AbstractHorse absHorse) {
    super(absHorse);
  }

  /**
   * <p>
   * コンストラクタ（ItemStack）.
   * </p>
   *
   * @param metaData HorseEggsのmeta情報
   */
  public MuleEggData(HashMap<String, ?> metaData) {
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
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
