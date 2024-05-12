/**
 * Factory for LoreWriter.
 */

package wacky.horseeggs.EntityWriter.factory;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import wacky.horseeggs.EntityWriter.DonkyEntityWriter;
import wacky.horseeggs.EntityWriter.EntityWriter;
import wacky.horseeggs.EntityWriter.HorseEntityWriter;
import wacky.horseeggs.EntityWriter.LlamaEntityWriter;
import wacky.horseeggs.EntityWriter.MuleEntityWriter;
import wacky.horseeggs.EntityWriter.SkeltonHorseEntityWriter;
import wacky.horseeggs.EntityWriter.TraderLlamaEntityWriter;
import wacky.horseeggs.EntityWriter.ZombieHorseEntityWriter;
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
  public static EntityWriter newLoreWriter(EntityType entityType, AbstractHorse absHorse) {
    switch (entityType) {
      case LLAMA:
        return new LlamaEntityWriter(absHorse);
      case MULE:
        return new MuleEntityWriter(absHorse);
      case DONKEY:
        return new DonkyEntityWriter(absHorse);
      case HORSE:
        return new HorseEntityWriter(absHorse);
      case ZOMBIE_HORSE:
        return new ZombieHorseEntityWriter(absHorse);
      case SKELETON_HORSE:
        return new SkeltonHorseEntityWriter(absHorse);
      case TRADER_LLAMA:
        return new TraderLlamaEntityWriter(absHorse);
      default:
        return null;
    }
  }
}
