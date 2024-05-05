/**
 * 
 */
package wacky.horseeggs.eggData;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

/**
 * Base class for HorseEgg Data.
 */
public abstract class EggDataBase {
  /**
   * 
   */
  public EggDataBase() {
    // TODO 自動生成されたコンストラクター・スタブ
  }

  public static final String ENTITYEGG_CONTENT_KEY = "EntityEgg";
  public static final String ENTITYEGG_CONTENT_EMPTY= "Empty";
  public static final String ENTITYEGG_NBT_KEY = "EntityEgg_NBT";
  public static final String ENTITYEGG_TIMESTAMP_KEY = "EntityEggTimeStump";

  public static final String EGG_NAME = "HorseEgg";

  public boolean hasInventory = false;

  public abstract EntityType getEntityType();

  public Material getEmptyEggMaterial() {
      return Material.GHAST_SPAWN_EGG;
  }

  public abstract Material getFilledEggMaterial();

  public EntityType getEmptyEggEntityType() {
      return EntityType.GHAST;
  }

  public abstract EntityType getFilledEggEntityType();

  public String getDisplayName() {
      return EGG_NAME;
  }

  public Material getRecipeMaterialA() {
      return Material.ENDER_PEARL;
  }

  public Material getRecipeMaterialB() {
      return Material.EGG;
  }

}
