/**
 * <p>
 * このクラスは ItemStack のツールチップで表示する、<br>
 * Loreの内容を出力する文字列を構築する機能を提供します.
 * </p>
 */

package wacky.horseeggs.LoreWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import wacky.horseeggs.eggData.EggDataBase;

/**
 * Base class for Write to Lore on HorseEgg ItemStack.
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
  private String equipmentLore;
  private String speedLore;
  private String strengthLore;

  protected List<String> generateLore(EggDataBase eggData) {
    return new ArrayList<>() {
      {
        // 1. 体力
        String health = getHealth(eggData).toString();
        if (StringUtils.isNotBlank(health)) {
          healthLore = health;
          add(healthLore);
        }
        // 2. スピード
        String speed = getSpeed(eggData).toString();
        if (StringUtils.isNotBlank(speed)) {
          speedLore = speed;
          add(speedLore);
        }
        // 3. 跳躍力
        String height = getHeight(eggData).toString();
        if (StringUtils.isNotBlank(height)) {
          heightLore = height;
          add(heightLore);
        }
        // 4. 運搬力
        String strength = getStrength(eggData).toString();
        if (StringUtils.isNotBlank(strength)) {
          strengthLore = strength;
          add(strengthLore);
        }
        // 5. カラー、スタイル
        String colorStyle = getColorStyle(eggData).toString();
        if (StringUtils.isNotBlank(colorStyle)) {
          colorStyleLore = colorStyle;
          add(colorStyleLore);
        }
        // 6. オーナー
        String owner = getOwner(eggData).toString();
        if (StringUtils.isNotBlank(owner)) {
          ownerLore = owner;
          add(ownerLore);
        }
        // 7. 装備品（サドル、アーマー、チェスト）
        String equipment = getEquipment(eggData).toString();
        if (StringUtils.isNotBlank(equipment)) {
          equipmentLore = equipment;
          add(equipmentLore);
        }
      }
    };
  }

  public abstract List<String> generateLore(Entity entity);

  private StringBuilder getEquipment(EggDataBase eggData) {
    StringBuilder equipmentSb = new StringBuilder();
    String saddle = this.getSaddle(eggData).toString();
    if (StringUtils.isNotBlank(saddle)) {
      equipmentSb.append(saddle);
    }
    String armor = this.getArmor(eggData).toString();
    if (StringUtils.isNotBlank(armor)) {
      equipmentSb.append(armor);
    }
    String chest = this.getChest(eggData).toString();
    if (StringUtils.isNotBlank(chest)) {
      equipmentSb.append(chest);
    }
    return equipmentSb;
  }

  private StringBuilder getColorStyle(EggDataBase eggData) {
    StringBuilder colorStyleSb = new StringBuilder();
    String color = eggData.getColor();
    if (StringUtils.isNotBlank(color)) {
      colorStyleSb.append(color);
    }
    String style = eggData.getStyle();
    if (StringUtils.isNotBlank(style)) {
      colorStyleSb.append(SPLITTER);
      colorStyleSb.append(style);
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
    String owner = eggData.getOwner();
    if (StringUtils.isNotBlank(owner)) {
      ownerSb.append(LABEL_OWNER);
      ownerSb.append(owner);
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
