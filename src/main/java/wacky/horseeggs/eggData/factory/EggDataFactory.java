package wacky.horseeggs.eggData.factory;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
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
  public EggDataBase newEggData(EntityType entityType, AbstractHorse horse){
    switch (entityType) {
      case LLAMA:
        return new LlamaEggData(horse);
      case MULE:
        return new MuleEggData(horse);
      case DONKEY:
        return new DonkeyEggData(horse);
      case HORSE:
        return new HorseEggData(horse);
      case ZOMBIE_HORSE:
        return new ZombieHorseEggData(horse);
      case SKELETON_HORSE:
        return new SkeletonHorseEggData(horse);
      case TRADER_LLAMA:
        return new TraderLlamaEggData(horse);
      default:
        return null;
    }
  }
  public EggDataBase newEggData(Material eggType, HashMap<String, ?> metaData){
    switch (eggType) {
      case LLAMA_SPAWN_EGG:
        return new LlamaEggData(metaData);
      case MULE_SPAWN_EGG:
        return new MuleEggData(metaData);
      case DONKEY_SPAWN_EGG:
        return new DonkeyEggData(metaData);
      case HORSE_SPAWN_EGG:
        return new HorseEggData(metaData);
      case ZOMBIE_HORSE_SPAWN_EGG:
        return new ZombieHorseEggData(metaData);
      case SKELETON_HORSE_SPAWN_EGG:
        return new SkeletonHorseEggData(metaData);
      case TRADER_LLAMA_SPAWN_EGG:
        return new TraderLlamaEggData(metaData);
      default:
        return null;
    }
  }
}
