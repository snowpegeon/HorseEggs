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
 * ロバを捕獲するためのHorseEggの実体クラス.
 */
public class DonkeyEggData extends EggDataBase {

  /**
   * デフォルトコンストラクタ.
   *
   * @deprecated
   *     <p>
   *     このコンストラクタは通常使用しないでください。<br> 使用目的別で、各コンストラクタを呼び出してください。<br>
   *     キャプチャー：{@link DonkeyEggData#DonkeyEggData(AbstractHorse)}<br>
   *     リリース：{@link DonkeyEggData#DonkeyEggData(HashMap)}
   *     </p>
   */
  public DonkeyEggData() {
    super();
  }

  /**
   * コンストラクタ（AbstractHorse）
   * @param absHorse スポーン中の馬情報.
   */
  public DonkeyEggData(AbstractHorse absHorse){
    super(absHorse);
  }

  /**
   * コンストラクタ（ItemStack）
   * @param metaData HorseEggsのアイテム
   */
  public DonkeyEggData(HashMap<String, ?> metaData){
    super(metaData);
  }

  /**
   * 卵に格納できるエンティティタイプを取得.
   *
   * @return {@link EntityType}.
   */
  @Override
  public EntityType getEntityType() {
    return EntityType.DONKEY;
  }

  @Override
  public Material getFilledEggMaterial() {
    return Material.DONKEY_SPAWN_EGG;
  }

  @Override
  public EntityType getFilledEggEntityType() {
    return EntityType.DONKEY;
  }

}
