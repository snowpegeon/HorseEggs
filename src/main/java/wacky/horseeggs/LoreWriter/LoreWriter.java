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
 * <p>
 * HorseEgg の ItemStack {@link org.bukkit.inventory.ItemStack} の<br>
 * Lore へ書き込む文字列を構築する機能の実装クラス.
 * </p>
 */
@Getter
public abstract class LoreWriter {
  /**
   * <p>
   * チェストのマテリアル名<br>
   * {@link Material.CHEST}.
   * </p>
   */
  private static final String ITEM_CHEST = Material.CHEST.name();

  /**
   * <p>
   * サドルのマテリアル名<br>
   * {@link Material.SADDLE}.
   * </p>
   */
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

  /**
   * <p>
   * カラー、スタイル.
   * </p>
   */
  private String colorStyleLore;

  /**
   * <p>
   * 体力（整数値のみ） [残Health] / [最大Health].
   * </p>
   */
  private String healthLore;

  /**
   * <p>
   * 跳躍力（5文字）.
   * </p>
   */
  private String heightLore;

  /**
   * <p>
   * オーナー（テイムした人、プレイヤー名）.
   * </p>
   */
  private String ownerLore;

  /**
   * <p>
   * 装備品（サドル、ウマ鎧、カーペット、チェスト）.
   * </p>
   */
  private String equipmentLore;

  /**
   * <p>
   * 移動速度（6文字）.
   * </p>
   */
  private String speedLore;

  /**
   * <p>
   * 運搬力（ラマ、行商人のラマ）.
   * </p>
   */
  private String strengthLore;
  
  @Getter
  private List<String> loreList;
  
  public LoreWriter(EggDataBase eggData) {
    this.loreList = this.generateLore(eggData);
  }
  
  /**
   * <p>
   * ItemStack でツールチップとして表示するLore文字列を構築します.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return Lore {@link ArrayList}
   */
  private List<String> generateLore(EggDataBase eggData) {
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

  /**
   * <p>
   * 装備品のLore文字列を構築します<br>
   * 装備品が1つも無い場合は、空の {@link StringBuilder} を返します<br>
   * <br>
   * <b>パターン：</b><br>
   * ウマ：<i>[SADDLE][ウマ鎧名]</i><br>
   * ロバ、ラバ：<i>[SADDLE][CHEST]</i><br>
   * スケルトンホース：<i>[SADDLE]</i><br>
   * ラマ、行商人のラマ：<i>[カーペット名][CHEST]</i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 装備品のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * カラーとスタイルのLore文字列を構築します<br>
   * カラーが無い場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * ウマ：<i>[カラー名]/[スタイル名]</i><br>
   * ラマ、行商人のラマ：<i>[カラー名]</i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   * 
   * @return カラー、スタイルのLore文字列 {@link StringBuilder}
   */
  private StringBuilder getColorStyle(EggDataBase eggData) {
    StringBuilder colorStyleSb = new StringBuilder();
    String color = eggData.getColor();
    if (StringUtils.isNotBlank(color)) {
      colorStyleSb.append(color);
      String style = eggData.getStyle();
      if (StringUtils.isNotBlank(style)) {
        colorStyleSb.append(SPLITTER);
        colorStyleSb.append(style);
      }
    }
    return colorStyleSb;
  }

  /**
   * <p>
   * チェストのアイテム名をLore文字列として構築します<br>
   * チェストを保持していない場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * チェストを保持している場合：<i>[チェストのマテリアル名]</i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return チェストのアイテム名のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * 体力をLore文字列として構築します<br>
   * 体力は必ず保持しています<br>
   * 保持していない場合は、空の {@link StringBuilder} を返しますが、<br>
   * これはキャッチしたエンティティが通常でない可能性があります<br>
   * <b>パターン：</b><br>
   * <i> {@value LoreWriter#LABEL_HEALTH} [現在体力] {@value LoreWriter#SPLITTER}[最大体力] </i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 体力のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * 跳躍力のLore文字列を構築します<br>
   * 跳躍力は必ず保持しています<br>
   * 保持していない場合は、空の {@link StringBuilder} を返しますが、<br>
   * これはキャッチしたエンティティが通常でない可能性があります<br>
   * <b>パターン：</b><br>
   * <i> {@value LoreWriter#LABEL_HEIGHT}[跳躍力] </i>.
   * </p>
   *
   * @apiNote 小数点を含む5文字がセットされます
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 跳躍力のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * オーナーをLore文字列として構築します<br>
   * テイムされていないエンティティの場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * オーナーを保持している場合：<i> {@value LoreWriter#LABEL_OWNER} [オーナー]</i>.
   * </p>
   *
   * @apiNote オーナーはテイムした人、プレーヤー名です
   *
   * @param eggData {@link EggDataBase}
   *
   * @return オーナーのLore文字列 {@link StringBuilder}
   */
  private StringBuilder getOwner(EggDataBase eggData) {
    StringBuilder ownerSb = new StringBuilder();
    String owner = eggData.getOwner();
    if (StringUtils.isNotBlank(owner)) {
      ownerSb.append(LABEL_OWNER);
      ownerSb.append(owner);
    }
    return ownerSb;
  }

  /**
   * <p>
   * サドルのアイテム名をLore文字列として構築します<br>
   * サドルを保持していない場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * サドルを保持している場合：<i>[サドルのマテリアル名]</i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return サドルのアイテム名のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * 馬鎧のアイテム名をLore文字列として構築します<br>
   * 馬鎧を保持していない場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * 馬鎧を保持している場合：<i>[馬鎧のマテリアル名]</i>.
   * </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 馬鎧のアイテム名のLore文字列 {@link StringBuilder}
   */
  private StringBuilder getArmor(EggDataBase eggData) {
    StringBuilder armorSb = new StringBuilder();
    if (Objects.nonNull(eggData.getArmor())) {
      armorSb.append(VALUE_PREFIX);
      armorSb.append(eggData.getArmor());
      armorSb.append(VALUE_SUFFIX);
    }
    return armorSb;
  }

  /**
   * <p>
   * 地上移動速度のLore文字列を構築します<br>
   * 地上移動速度は必ず保持しています<br>
   * 保持していない場合は、空の {@link StringBuilder} を返しますが、<br>
   * これはキャッチしたエンティティが通常でない可能性があります<br>
   * <b>パターン：</b><br>
   * <i> {@value LoreWriter#LABEL_SPEED}[地上移動速度] </i>.
   * </p>
   *
   * @apiNote 小数点を含む6文字がセットされます
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 地上移動速度のLore文字列 {@link StringBuilder}
   */
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

  /**
   * <p>
   * 運搬力のLore文字列を構築します<br>
   * 運搬力を保持していない場合は、空の {@link StringBuilder} を返します<br>
   * <b>パターン：</b><br>
   * 運搬力を保持している場合：<i>{@value LoreWriter#LABEL_STRENGTH} [運搬力]</i>.
   * </p>
   *
   * @apiNote
   *          <p>
   *          運搬力はラマ、行商人のラマのみに存在します<br>
   *          運搬力は搭載したチェストのインベントリの広さを示します
   *          </p>
   *          <p>
   *          例）運搬力が3のとき<br>
   *          <table style="border: 1px solid gray;">
   *          <tr style="border: 1px solid gray;">
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          </tr>
   *          <tr style="border: 1px solid gray;">
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          </tr>
   *          <tr style="border: 1px solid gray;">
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          <td style="border: 1px solid gray;">■</td>
   *          </tr>
   *          </table>
   *          </p>
   *
   * @param eggData {@link EggDataBase}
   *
   * @return 運搬力のLore文字列 {@link StringBuilder}
   */
  private StringBuilder getStrength(EggDataBase eggData) {
    StringBuilder strengthSb = new StringBuilder();
    if (Objects.nonNull(eggData.getStrength())) {
      strengthSb.append(LABEL_STRENGTH);
      strengthSb.append(eggData.getStrength());
    }
    return strengthSb;
  }
}
