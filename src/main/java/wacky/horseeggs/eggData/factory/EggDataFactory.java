package wacky.horseeggs.eggData.factory;

import static org.bukkit.Material.DONKEY_SPAWN_EGG;
import static org.bukkit.Material.HORSE_SPAWN_EGG;
import static org.bukkit.Material.LLAMA_SPAWN_EGG;
import static org.bukkit.Material.MULE_SPAWN_EGG;
import static org.bukkit.Material.SKELETON_HORSE_SPAWN_EGG;
import static org.bukkit.Material.TRADER_LLAMA_SPAWN_EGG;
import static org.bukkit.Material.ZOMBIE_HORSE_SPAWN_EGG;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
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

  private static final Set<Material> CAPTURE_ABLE_EGG_MATERIALS = Collections.unmodifiableSet(
      EnumSet.of(LLAMA_SPAWN_EGG, MULE_SPAWN_EGG, DONKEY_SPAWN_EGG, HORSE_SPAWN_EGG,
          ZOMBIE_HORSE_SPAWN_EGG, SKELETON_HORSE_SPAWN_EGG, TRADER_LLAMA_SPAWN_EGG));

  public static EggDataBase newEggData(EntityType entityType, AbstractHorse horse) {
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

  public static EggDataBase newEggData(Material eggType, HashMap<String, ?> metaData) {
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

  public static boolean isCaptureAbleEggMaterial(Material eggType) {
    return CAPTURE_ABLE_EGG_MATERIALS.contains(eggType);
  }

}
