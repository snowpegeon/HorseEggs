/**
 * <p>このクラスは ItemStack の ツールチップで表示する、<br>
 * Loreの内容を出力する機能を提供します.</p>
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
 * Base class for Write to  Lore on HorseEgg ItemStack.
 */
@Getter
public abstract class LoreWriter {
  /** {@link Material.CHEST}. */
  private static final String ITEM_CHEST = Material.CHEST.name();
  /** {@link Material.SADDLE}. */
  private static final String ITEM_SADDLE = Material.SADDLE.name();
  /**
   * <p>
   * Health lore label. {@value}
   * </p>
   */
  private static final String LABEL_HEALTH = "HP: ";
  /**
   * <p>
   * Jump height lore label. {@value}
   * </p>
   */
  private static final String LABEL_HEIGHT = "Height: ";
  /**
   * <p>
   * Owner lore label. {@value}
   * </p>
   */
  private static final String LABEL_OWNER = "Owner: ";
  /**
   * <p>
   * Speed lore label. {@value}
   * </p>
   */
  private static final String LABEL_SPEED = "Speed: ";
  /**
   * <p>
   * Strength lore label. {@value}
   * </p>
   */
  private static final String LABEL_STRENGTH = "Strength: ";

  /**
   * <p>
   * Lore text splitter. {@value}
   * </p>
   */
  private static final String SPLITTER = "/";
  /**
   * <p>
   * Value prefix. {@value}
   * </p>
   */
  private static final String VALUE_PREFIX = "[";
  /**
   * <p>
   * Value suffix. {@value}
   * </p>
   */
  private static final String VALUE_SUFFIX = "]";

  private String colorStyleLore;
  private String healthLore;
  private String heightLore;
  private String ownerLore;
  private String saddleArmorChestLore;
  private String speedLore;
  private String strengthLore;

  protected List<String> generateLore(EggDataBase eggData) {
    List<String> loreList = new ArrayList<>();

    // 1. 体力
    StringBuilder health = this.getHealth(eggData);
    if (BooleanUtils.isFalse(health.isEmpty())) {
      String healthLore = health.toString();
      this.healthLore = healthLore;
      loreList.add(healthLore);
    }

    // 2. スピード
    StringBuilder speed = this.getSpeed(eggData);
    if (BooleanUtils.isFalse(speed.isEmpty())) {
      String speedLore = speed.toString();
      this.speedLore = speedLore;
      loreList.add(speedLore);
    }

    // 3. 跳躍力
    StringBuilder height = this.getHeight(eggData);
    if (BooleanUtils.isFalse(height.isEmpty())) {
      String heightLore = height.toString();
      this.heightLore = heightLore;
      loreList.add(heightLore);
    }

    // 4. 運搬力
    StringBuilder strength = this.getStrength(eggData);
    if (BooleanUtils.isFalse(strength.isEmpty())) {
      String strengthLore = strength.toString();
      this.strengthLore = strengthLore;
      loreList.add(strengthLore);
    }

    // 5. カラー、スタイル
    StringBuilder colorStyle = this.getColorStyle(eggData);
    if (BooleanUtils.isFalse(colorStyle.isEmpty())) {
      String colorStyleLore = colorStyle.toString();
      this.colorStyleLore = colorStyleLore;
      loreList.add(colorStyleLore);
    }

    // 6. オーナー
    StringBuilder owner = this.getOwner(eggData);
    if (BooleanUtils.isFalse(owner.isEmpty())) {
      String ownerLore = owner.toString();
      this.ownerLore = ownerLore;
      loreList.add(ownerLore);
    }

    // 7. サドル、アーマー、チェスト
    StringBuilder saddleArmorChest = new StringBuilder();
    StringBuilder saddle = this.getSaddle(eggData);
    if (BooleanUtils.isFalse(saddle.isEmpty())) {
      saddleArmorChest.append(saddle.toString());
    }
    StringBuilder armor = this.getArmor(eggData);
    if (BooleanUtils.isFalse(armor.isEmpty())) {
      saddleArmorChest.append(armor.toString());
    }
    StringBuilder chest = this.getChest(eggData);
    if (BooleanUtils.isFalse(chest.isEmpty())) {
      saddleArmorChest.append(chest.toString());
    }
    if (BooleanUtils.isFalse(saddleArmorChest.isEmpty())) {
      String saddleArmorChestLore = saddleArmorChest.toString();
      this.saddleArmorChestLore = saddleArmorChestLore;
      loreList.add(saddleArmorChestLore);
    }

    return loreList;
  }

  public abstract List<String> generateLore(Entity entity);

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

  private StringBuilder getChest(EggDataBase eggData) {
    StringBuilder chestSb = new StringBuilder();
    if (Objects.nonNull(eggData.getIsCarryingChest())) {
      if (eggData.getIsCarryingChest()) {
        chestSb.append(VALUE_PREFIX);
        chestSb.append(ITEM_CHEST);
        chestSb.append(VALUE_SUFFIX);
      }
    }
    return chestSb;
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

  private StringBuilder getArmor(EggDataBase eggData) {
    StringBuilder armorSb = new StringBuilder();
    if (Objects.nonNull(eggData.getArmor())) {
      armorSb.append(VALUE_PREFIX);
      armorSb.append(eggData.getArmor());
      armorSb.append(VALUE_SUFFIX);
    }
    return armorSb;
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
