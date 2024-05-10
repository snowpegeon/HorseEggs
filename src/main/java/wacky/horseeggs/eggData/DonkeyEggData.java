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
public class DonkeyEggData extends EggDataBase {

  /**
   * コンストラクタ
   */
  public DonkeyEggData() {
    super();
    // TODO 自動生成されたコンストラクター・スタブ
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
