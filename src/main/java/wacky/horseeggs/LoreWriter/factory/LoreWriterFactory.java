/**
 * Factory for LoreWriter.
 */

package wacky.horseeggs.LoreWriter.factory;

import org.bukkit.entity.EntityType;
import wacky.horseeggs.LoreWriter.DonkeyLoreWriter;
import wacky.horseeggs.LoreWriter.HorseLoreWriter;
import wacky.horseeggs.LoreWriter.LlamaLoreWriter;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.LoreWriter.MuleLoreWriter;
import wacky.horseeggs.LoreWriter.SkeletonHorseLoreWriter;
import wacky.horseeggs.LoreWriter.TraderLlamaLoreWriter;
import wacky.horseeggs.LoreWriter.ZombieHorseLoreWriter;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * Factory class of LoreWriter.
 */
public class LoreWriterFactory {
  /**
   * Factory method for LoreWriter.
   *
   * @param entityType {@link EntityType}
   */
//  public LoreWriter newLoreWriter(EntityType entityType) {
//    switch (entityType) {
//      case LLAMA:
//        return new LlamaLoreWriter();
//      case MULE:
//        return new MuleLoreWriter();
//      case DONKEY:
//        return new DonkeyLoreWriter();
//      case HORSE:
//        return new HorseLoreWriter();
//      case ZOMBIE_HORSE:
//        return new ZombieHorseLoreWriter();
//      case SKELETON_HORSE:
//        return new SkeletonHorseLoreWriter();
//      case TRADER_LLAMA:
//        return new TraderLlamaLoreWriter();
//      default:
//        return null;
//    }
//  }
  
  /**
   * Factory method for LoreWriter.
   *
   * @param entityType {@link EntityType}
   */
  public static LoreWriter newLoreWriter(EntityType entityType, EggDataBase eggData) {
    switch (entityType) {
      case LLAMA:
        return new LlamaLoreWriter(eggData);
      case MULE:
        return new MuleLoreWriter(eggData);
      case DONKEY:
        return new DonkeyLoreWriter(eggData);
      case HORSE:
        return new HorseLoreWriter(eggData);
      case ZOMBIE_HORSE:
        return new ZombieHorseLoreWriter(eggData);
      case SKELETON_HORSE:
        return new SkeletonHorseLoreWriter(eggData);
      case TRADER_LLAMA:
        return new TraderLlamaLoreWriter(eggData);
      default:
        return null;
    }
  }
}
