/**
 * Factory for LoreWriter.
 */

package wacky.horseeggs.EntityWriter.factory;

import org.bukkit.entity.EntityType;
import wacky.horseeggs.EntityWriter.EntityWriter;
import wacky.horseeggs.EntityWriter.HorseEntityWriter;
import wacky.horseeggs.LoreWriter.DonkeyLoreWriter;
import wacky.horseeggs.LoreWriter.HorseLoreWriter;
import wacky.horseeggs.LoreWriter.LlamaLoreWriter;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.LoreWriter.MuleLoreWriter;
import wacky.horseeggs.LoreWriter.SkeletonHorseLoreWriter;
import wacky.horseeggs.LoreWriter.TraderLlamaLoreWriter;
import wacky.horseeggs.LoreWriter.ZombieHorseLoreWriter;

/**
 * Factory class of LoreWriter.
 */
public class EntityWriterFactory {
  /**
   * Factory method for LoreWriter.
   *
   * @param entityType {@link EntityType}
   */
  public EntityWriter newLoreWriter(EntityType entityType) {
    switch (entityType) {
      case LLAMA:
//        return new LlamaLoreWriter();
      case MULE:
//        return new MuleLoreWriter();
      case DONKEY:
//        return new DonkeyLoreWriter();
      case HORSE:
//        return new HorseEntityWriter();
      case ZOMBIE_HORSE:
//        return new ZombieHorseLoreWriter();
      case SKELETON_HORSE:
//        return new SkeletonHorseLoreWriter();
      case TRADER_LLAMA:
//        return new TraderLlamaLoreWriter();
      default:
        return null;
    }
  }
}
