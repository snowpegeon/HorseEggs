/**

 *
 */

package wacky.horseeggs.LoreWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.BooleanUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import lombok.Getter;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * Base class Writing for HorseEgg ItemStack Lore.
 */
@Getter
public abstract class LoreWriter {
  /** {@link Material.CHEST}. */
  private final static String ITEM_CHEST = Material.CHEST.name();
  /** {@link Material.SADDLE}. */
  private final static String ITEM_SADDLE = Material.SADDLE.name();
  /**
   * <p>Health lore label. {@value}</p>
   */
  private final static String LABEL_HEALTH = "HP: ";
  /**
   * <p>Jump height lore label. {@value}</p>
   */
  private final static String LABEL_HEIGHT = "Height: ";
  /**
   * <p>Owner lore label. {@value}</p>
   */
  private final static String LABEL_OWNER = "Owner: ";
  /**
   * <p>Speed lore label. {@value}</p>
   */
  private final static String LABEL_SPEED = "Speed: ";
  /**
   * <p>Strength lore label. {@value}</p>
   */
  private final static String LABEL_STRENGTH = "Strength: ";

  /**
   * <p>Lore text splitter. {@value}</p>
   */
  private final static String SPLITTER = "/";
  /**
   * <p>Value prefix. {@value}</p>
   */
  private final static String VALUE_PREFIX = "[";
  /**
   * <p>Value suffix. {@value}</p>
   */
  private final static String VALUE_SUFFIX = "]";

  private String colorLore;
  private String colorStyleLore;
  private String decorChestLore;
  private String healthLore;
  private String heightLore;
  private String ownerLore;
  private String saddleArmorLore;
  private String saddleChestLore;
  private String saddleLore;
  private String speedLore;
  private String strengthLore;

  protected List<String> generateLore(EggDataBase eggData) {
    List<String> loreList = new ArrayList<>();

    StringBuilder health = this.getHealth(eggData);
    if (BooleanUtils.isFalse(health.isEmpty())) {
      loreList.add(health.toString());
    }

    StringBuilder speed = this.getSpeed(eggData);
    if (BooleanUtils.isFalse(speed.isEmpty())) {
      loreList.add(speed.toString());
    }

    StringBuilder height = this.getHeight(eggData);
    if (BooleanUtils.isFalse(height.isEmpty())) {
      loreList.add(height.toString());
    }

    StringBuilder colorStyle = this.getColorStyle(eggData);
    if (BooleanUtils.isFalse(colorStyle.isEmpty())) {
      loreList.add(colorStyle.toString());
    }

    StringBuilder owner = this.getOwner(eggData);
    if (BooleanUtils.isFalse(owner.isEmpty())) {
      eggData.getOwner();
    }

    StringBuilder strength = this.getStrength(eggData);
    if (BooleanUtils.isFalse(strength.isEmpty())) {
      loreList.add(strength.toString());
    }
    return loreList;
  }

  public abstract List<String> generateLore(Entity entity);

  private StringBuilder getColor(EggDataBase eggData) {
    StringBuilder colorSb = new StringBuilder();
    if (Objects.nonNull(eggData.getColor())) {
      colorSb.append(eggData.getColor());
    }
    return colorSb;
  }

  private StringBuilder getColorStyle(EggDataBase eggData) {
    StringBuilder colorStyleSb = new StringBuilder();
    if (Objects.nonNull(eggData.getColor())) {
      colorStyleSb.append(eggData.getColor());
      if (!colorStyleSb.isEmpty()) {
        colorStyleSb.append(SPLITTER);
      }
      colorStyleSb.append(eggData.getStyle());
    }
    if (Objects.nonNull(eggData.getStyle())) {
      if (!colorStyleSb.isEmpty()) {
        colorStyleSb.append(SPLITTER);
      }
      colorStyleSb.append(eggData.getStyle());
    }
    return colorStyleSb;
  }

  private StringBuilder getDecorChest(EggDataBase eggData) {
    StringBuilder decorChestSb = new StringBuilder();
    if (Objects.nonNull(eggData.getArmor())) {
      decorChestSb.append(VALUE_PREFIX);
      decorChestSb.append(eggData.getArmor());
      decorChestSb.append(VALUE_SUFFIX);
    }
    if (Objects.nonNull(eggData.getIsCarryingChest())) {
      if (eggData.getIsCarryingChest()) {
        decorChestSb.append(VALUE_PREFIX);
        decorChestSb.append(ITEM_CHEST);
        decorChestSb.append(VALUE_SUFFIX);
      }
    }
    return decorChestSb;
  }

  private StringBuilder getHealth(EggDataBase eggData) {
    StringBuilder healthSb = new StringBuilder();
    Double health = eggData.getHealth();
    Double maxHealth = eggData.getMaxHealth();
    if (Objects.nonNull(health) && Objects.nonNull(maxHealth)) {
      healthSb.append(LABEL_HEALTH);
      healthSb.append(health.intValue());
      healthSb.append(SPLITTER);
      healthSb.append(maxHealth.intValue());
    }
    return healthSb;
  }

  protected String getHealthMeter(double currentHealth, double maxHealth) {
    StringBuilder healthMeterSb = new StringBuilder();
    healthMeterSb.append("Health:[");
    healthMeterSb.append(ChatColor.GREEN);
    healthMeterSb.append("");
    int i = 0;
    double rate = currentHealth * 20 / maxHealth;
    for (; i < rate; i++) {
      healthMeterSb.append(":");
    }
    healthMeterSb.append(ChatColor.GRAY);
    healthMeterSb.append("");
    for (; i < 20; i++) {
      healthMeterSb.append(":");
    }
    healthMeterSb.append(ChatColor.DARK_PURPLE);
    healthMeterSb.append("](");
    healthMeterSb.append(currentHealth);
    healthMeterSb.append(" / ");
    healthMeterSb.append(maxHealth);
    healthMeterSb.append(")");
    return healthMeterSb.toString();
  }

  private StringBuilder getHeight(EggDataBase eggData) {
    StringBuilder heightSb = new StringBuilder();
    if (Objects.nonNull(eggData.getJump())) {
      double jump = eggData.getJump();
      heightSb.append(LABEL_HEIGHT);
      // ジャンプ高度算出
      // from Zyin's HUD、ジャンプ高度
      // See https://github.com/Zyin055/zyinhud
      double jumpHeight = 0d;
      while (jump > 0d) {
        jumpHeight += jump;
        jump -= 0.08;
        jump *= 0.98;
      }
      String heightValue = Double.toString(jumpHeight);
      final int digit5 = 5;
      if (heightValue.length() > digit5) {
        heightSb.append(heightValue.substring(0, digit5));
      }
      heightSb.append(heightValue);
    }
    return heightSb;
  }

  private StringBuilder getOwner(EggDataBase eggData) {
    StringBuilder ownerSb = new StringBuilder();
    if (Objects.nonNull(eggData.getOwner())) {
      ownerSb.append(LABEL_OWNER);
      ownerSb.append(eggData.getOwner());
    }
    return ownerSb;
  }

  private StringBuilder getSaddle(EggDataBase eggData) {
    StringBuilder saddleSb = new StringBuilder();
    if (Objects.nonNull(eggData.getIsSaddled())) {
      if (eggData.getIsSaddled()) {
        saddleSb.append(VALUE_PREFIX);
        saddleSb.append(ITEM_SADDLE);
        saddleSb.append(VALUE_SUFFIX);
      }
    }
    return saddleSb;
  }

  private StringBuilder getSaddleArmor(EggDataBase eggData) {
    StringBuilder saddleArmorSb = new StringBuilder();
    if (Objects.nonNull(eggData.getIsSaddled()) && Objects.nonNull(eggData.getArmor())) {
      if (eggData.getIsSaddled()) {
        saddleArmorSb.append(VALUE_PREFIX);
        saddleArmorSb.append(ITEM_SADDLE);
        saddleArmorSb.append(VALUE_SUFFIX);
      }
      saddleArmorSb.append(VALUE_PREFIX);
      saddleArmorSb.append(eggData.getArmor());
      saddleArmorSb.append(VALUE_SUFFIX);
    }
    return saddleArmorSb;
  }

  private StringBuilder getSaddleChest(EggDataBase eggData) {
    StringBuilder saddleChestSb = new StringBuilder();
    if (Objects.nonNull(eggData.getIsSaddled()) && Objects.nonNull(eggData.getIsCarryingChest())) {
      if (eggData.getIsSaddled()) {
        saddleChestSb.append(VALUE_PREFIX);
        saddleChestSb.append(ITEM_SADDLE);
        saddleChestSb.append(VALUE_SUFFIX);
      }
      if (eggData.getIsCarryingChest()) {
        saddleChestSb.append(VALUE_PREFIX);
        saddleChestSb.append(ITEM_CHEST);
        saddleChestSb.append(VALUE_SUFFIX);
      }
    }
    return saddleChestSb;
  }

  private StringBuilder getSpeed(EggDataBase eggData) {
    StringBuilder speedSb = new StringBuilder();
    if (Objects.nonNull(eggData.getSpeed())) {
      double speed = eggData.getSpeed();
      speedSb.append(LABEL_SPEED);
      String sppedValue = Double.toString(speed * 43);
      final int digit6 = 6;
      if (sppedValue.length() > digit6) {
        speedSb.append(sppedValue.substring(0, digit6));
      }
      speedSb.append(sppedValue);
    }
    return speedSb;
  }

  private StringBuilder getStrength(EggDataBase eggData) {
    StringBuilder strengthSb = new StringBuilder();
    if (Objects.nonNull(eggData.getStrength())) {
      strengthSb.append(LABEL_STRENGTH);
      strengthSb.append(eggData.getStrength());
    }
    return strengthSb;
  }
}
