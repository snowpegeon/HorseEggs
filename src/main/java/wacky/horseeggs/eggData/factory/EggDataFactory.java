package wacky.horseeggs.eggData.factory;

import org.bukkit.entity.EntityType;
import wacky.horseeggs.LoreWriter.DonkeyLoreWriter;
import wacky.horseeggs.LoreWriter.HorseLoreWriter;
import wacky.horseeggs.LoreWriter.LlamaLoreWriter;
import wacky.horseeggs.LoreWriter.MuleLoreWriter;
import wacky.horseeggs.LoreWriter.SkeletonHorseLoreWriter;
import wacky.horseeggs.LoreWriter.TraderLlamaLoreWriter;
import wacky.horseeggs.LoreWriter.ZombieHorseLoreWriter;
import wacky.horseeggs.eggData.DonkeyEggData;
import wacky.horseeggs.eggData.EggDataBase;
import wacky.horseeggs.eggData.HorseEggData;
import wacky.horseeggs.eggData.LlamaEggData;
import wacky.horseeggs.eggData.MuleEggData;
import wacky.horseeggs.eggData.SkeletonHorseEggData;
import wacky.horseeggs.eggData.TraderLlamaEggData;
import wacky.horseeggs.eggData.ZombieHorseEggData;

/**
 * Factory class of EggData.
 */
public class EggDataFactory {
  public EggDataBase newEggData(EntityType entityType){
    switch (entityType) {
      case LLAMA:
        return new LlamaEggData();
      case MULE:
        return new MuleEggData();
      case DONKEY:
        return new DonkeyEggData();
      case HORSE:
        return new HorseEggData();
      case ZOMBIE_HORSE:
        return new ZombieHorseEggData();
      case SKELETON_HORSE:
        return new SkeletonHorseEggData();
      case TRADER_LLAMA:
        return new TraderLlamaEggData();
      default:
        return null;
    }
  }
}
