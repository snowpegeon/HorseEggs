/**
 * Egg data of base data.
 */

package wacky.horseeggs.eggData;

import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.LlamaInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import wacky.horseeggs.LoreWriter.LoreWriter;
import wacky.horseeggs.LoreWriter.factory.LoreWriterFactory;
import wacky.horseeggs.eggData.factory.EggDataFactory;

/**
 * {@link EggDataBase} クラスを検証するテストクラスです.
 */
// @Ignore("保留中")
public class EggDataBaseTest {
  @Mock
  Horse horse;

  @Mock
  Donkey donkey;

  @Mock
  Llama llama;

  @Mock
  AbstractHorse absHorse;

  @Mock
  AbstractHorseInventory absHorseInv;

  @Mock
  HorseInventory horseInv;

  @Mock
  LlamaInventory llamaInv;

  @Mock
  AttributeInstance attr;

  @Mock
  AnimalTamer animalTamer;

  @Mock
  ChestedHorse chestedHorse;

  @Mock
  ItemMeta itemMeta;

  @Mock
  Server server;

  @Mock
  ItemFactory itemFactory;

  /**
   * 事前準備.
   *
   * @throws java.lang.Exception すべての例外
   */
  @Before
  public void setUp() throws Exception {
    openMocks();
  }

  private void openMocks() {
    MockitoAnnotations.openMocks(this);
  }

  private void setUpHorse() {
    absHorse = horse;
    when(horse.getColor()).thenReturn(Horse.Color.BLACK);
    when(horse.getStyle()).thenReturn(Horse.Style.NONE);

    when(animalTamer.getName()).thenReturn("ウマの飼い主");
    final UUID uuid = new UUID(2968001111801612278L, -6995725480010122770L);
    when(animalTamer.getUniqueId()).thenReturn(uuid);

    when(absHorse.getInventory()).thenReturn(horseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.HORSE);
    when(absHorse.getType()).thenReturn(EntityType.HORSE);
    when(absHorse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).thenReturn(attr);
    when(absHorse.getAttribute(Attribute.GENERIC_MAX_HEALTH)).thenReturn(attr);
    when(absHorse.getCustomName()).thenReturn("うまいウマ");
    when(absHorse.isTamed()).thenReturn(true);
    when(absHorse.getOwner()).thenReturn(animalTamer);

  }

  private void setUpDonkey() {
    absHorse = donkey;
    when(absHorse.getInventory()).thenReturn(absHorseInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.DONKEY);
    when(absHorse.getType()).thenReturn(EntityType.DONKEY);
    when(chestedHorse.isCarryingChest()).thenReturn(false);

  }

  private void setUpLlama() {
    absHorse = llama;
    when(llama.getColor()).thenReturn(Llama.Color.WHITE);

    when(absHorse.getInventory()).thenReturn(llamaInv);
    when(absHorse.getVariant()).thenReturn(Horse.Variant.LLAMA);
    when(absHorse.getType()).thenReturn(EntityType.LLAMA);

  }

  /**
   * {@link EggDataBase#EggDataBase()} のためのテスト・メソッド.
   */
  @Test
  public final void testEggDataBase() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData));
  }

  /**
   * {@link EggDataBase#EggDataBase(AbstractHorse)} のためのテスト・メソッド.
   */
  @Test
  public final void testEggDataBaseAbstractHorse() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData));
  }

  /**
   * {@link EggDataBase#EggDataBase(java.util.HashMap)} のためのテスト・メソッド.
   */
  @Test
  public final void testEggDataBaseHashMapOfStringQ() {
    HashMap<String, Object> metaData = null;
    // ウマ
    metaData = new HashMap<String, Object>() {
      {
        put("Chest", (byte) 0);
        put("Speed", 1.0d);
        put("Health", 30.0d);
        put("UUIDLeast", -6995725480010122770L);
        put("Color", "CHESTNUT");
        put("Jump", 1.0d);
        put("MaxHealth", 30.0d);
        put("Name", "うまいウマ");
        put("Saddle", (byte) 1);
        put("Variant", "HORSE");
        put("Type", "HORSE");
        put("UUIDMost", 2968001111801612278L);
        put("Armor", "LEATHER_HORSE_ARMOR");
        put("Style", "WHITEFIELD");
        put("ArmorColor", "255,29,29,33");
      }
    };
    this.setUpHorse();
    EggDataBase horseEggData = EggDataFactory.newEggData(Material.HORSE_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(horseEggData));
    
    // ラマ
    metaData = new HashMap<String, Object>() {
      {
        put("Chest", (byte) 1);
        put("Speed", 0.17499999701976776d);
        put("Health", 21.0d);
        put("UUIDLeast", -6995725480010122770L);
        put("Color", "GRAY");
        put("Jump", 0.5d);
        put("MaxHealth", 21.0d);
        put("Saddle", (byte) 0);
        put("Variant", "LLAMA");
        put("Type", "LLAMA");
        put("UUIDMost", 2968001111801612278L);
        put("Armor", "PURPLE_CARPET");
        put("Strength", 3);
      }
    };
    this.setUpLlama();
    EggDataBase llamaEggData = EggDataFactory.newEggData(Material.LLAMA_SPAWN_EGG, metaData);
    Assert.assertTrue(Objects.nonNull(llamaEggData));
  }

  /**
   * {@link EggDataBase#getDisplayName()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDisplayName() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getDisplayName()));
  }

  /**
   * {@link EggDataBase#getEmptyEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEmptyEggEntityType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEmptyEggEntityType()));
  }

  /**
   * {@link EggDataBase#getEmptyEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEmptyEggMaterial() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEmptyEggMaterial()));
  }

  /**
   * {@link EggDataBase#getEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityType()));
  }

  /**
   * {@link EggDataBase#getFilledEggEntityType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggEntityType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggEntityType()));
  }

  /**
   * {@link EggDataBase#getFilledEggMaterial()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetFilledEggMaterial() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getFilledEggMaterial()));
  }

  /**
   * {@link EggDataBase#getRecipeMaterialA()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetRecipeMaterialA() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getRecipeMaterialA()));
  }

  /**
   * {@link EggDataBase#getRecipeMaterialB()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetRecipeMaterialB() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getRecipeMaterialB()));
  }

  /**
   * {@link EggDataBase#setLoreList(List)} のためのテスト・メソッド.
   */
  @Test
  public final void testSetLoreList() {
    this.setUpHorse();

    EntityType entityType = EntityType.HORSE;

    EggDataBase eggData = EggDataFactory.newEggData(entityType, absHorse);

    LoreWriter lw = LoreWriterFactory.newLoreWriter(entityType, eggData);
    var entityWriterloreList = lw.generateLore(eggData);
    eggData.setLoreList(entityWriterloreList);
    Assert.assertTrue(Objects.nonNull(eggData.getLoreList()));
  }

  /**
   * {@link EggDataBase#isHasInventory()} のためのテスト・メソッド.
   */
  @Test
  public final void testIsHasInventory() {
    this.setUpDonkey();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.isHasInventory()));
  }

  /**
   * {@link EggDataBase#getDataKeyDisplay()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyDisplay() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyDisplay(), "display");
  }

  /**
   * {@link EggDataBase#getDataKeyLore()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyLore() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyLore(), "Lore");
  }

  /**
   * {@link EggDataBase#getDataKeyEntityTag()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyEntityTag() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyEntityTag(), "EntityTag");
  }

  /**
   * {@link EggDataBase#getDataKeyId()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyId() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyId(), "id");
  }

  /**
   * {@link EggDataBase#getDataKeyMinecraft()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyMinecraft() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyMinecraft(), NamespacedKey.MINECRAFT + ":");
  }

  /**
   * {@link EggDataBase#getDataKeyChest()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyChest() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyChest(), "Chest");
  }

  /**
   * {@link EggDataBase#getDataKeySpeed()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeySpeed() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeySpeed(), "Speed");
  }

  /**
   * {@link EggDataBase#getDataKeyHealth()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyHealth() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyHealth(), "Health");
  }

  /**
   * {@link EggDataBase#getDataKeyUuidLeast()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyUuidLeast() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyUuidLeast(), "UUIDLeast");
  }

  /**
   * {@link EggDataBase#getDataKeyUuidMost()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyUuidMost() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyUuidMost(), "UUIDMost");
  }

  /**
   * {@link EggDataBase#getDataKeyColor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyColor() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyColor(), "Color");
  }

  /**
   * {@link EggDataBase#getDataKeyJump()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyJump() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyJump(), "Jump");
  }

  /**
   * {@link EggDataBase#getDataKeyMaxHealth()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyMaxHealth() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyMaxHealth(), "MaxHealth");
  }

  /**
   * {@link EggDataBase#getDataKeyName()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyName() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyName(), "Name");
  }

  /**
   * {@link EggDataBase#getDataKeyType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyType(), "Type");
  }

  /**
   * {@link EggDataBase#getDataKeyVariant()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyVariant() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyVariant(), "Variant");
  }

  /**
   * {@link EggDataBase#getDataKeyArmor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyArmor() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyArmor(), "Armor");
  }

  /**
   * {@link EggDataBase#getDataKeyArmorColor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyArmorColor() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyArmorColor(), "ArmorColor");
  }

  /**
   * {@link EggDataBase#getDataKeyStyle()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyStyle() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyStyle(), "Style");
  }

  /**
   * {@link EggDataBase#getDataKeySaddle()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeySaddle() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeySaddle(), "Saddle");
  }

  /**
   * {@link EggDataBase#getDataKeyStrength()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDataKeyStrength() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertEquals(eggData.getDataKeyStrength(), "Strength");
  }

  /**
   * {@link EggDataBase#getLoreList()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetLoreList() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getLoreList()));
  }

  /**
   * {@link EggDataBase#getOwner()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetOwner() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getOwner()));
  }

  /**
   * {@link EggDataBase#getIsCarryingChest()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetIsCarryingChest() {
    this.setUpDonkey();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.DONKEY, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getIsCarryingChest()));
  }

  /**
   * {@link EggDataBase#getSpeed()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetSpeed() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getSpeed()));
  }

  /**
   * {@link EggDataBase#getHealth()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetHealth() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getHealth()));
  }

  /**
   * {@link EggDataBase#getColor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetColor() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getColor()));
  }

  /**
   * {@link EggDataBase#getJump()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetJump() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getJump()));
  }

  /**
   * {@link EggDataBase#getMaxHealth()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetMaxHealth() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getMaxHealth()));
  }

  /**
   * {@link EggDataBase#getName()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetName() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getName()));
  }

  /**
   * {@link EggDataBase#getIsSaddled()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetIsSaddled() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getIsSaddled()));
  }

  /**
   * {@link EggDataBase#getVariant()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetVariant() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getVariant()));
  }

  /**
   * {@link EggDataBase#getType()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetType() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getType()));
  }

  /**
   * {@link EggDataBase#getUuidLeast()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetUuidLeast() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getUuidLeast()));
  }

  /**
   * {@link EggDataBase#getUuidMost()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetUuidMost() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getUuidMost()));
  }

  /**
   * {@link EggDataBase#getArmor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetArmor() {
    this.setUpHorse();
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();
      bukkit.when(() -> Bukkit.getItemFactory()).thenReturn(itemFactory);

      when(server.getItemFactory()).thenReturn(itemFactory);

      ItemStack itemStack = new ItemStack(Material.LEATHER_HORSE_ARMOR, 1);
      when(horseInv.getArmor()).thenReturn(itemStack);

      EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
      Assert.assertTrue(Objects.nonNull(eggData.getArmor()));
    }
  }

  /**
   * {@link EggDataBase#getStyle()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetStyle() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getStyle()));
  }

  /**
   * {@link EggDataBase#getStrength()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetStrength() {
    this.setUpLlama();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.LLAMA, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getStrength()));
  }

  /**
   * {@link EggDataBase#getArmorColor()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetArmorColor() {
    this.setUpHorse();
    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {

      bukkit.when(() -> Bukkit.setServer(server)).thenCallRealMethod();
      bukkit.when(() -> Bukkit.getItemFactory()).thenReturn(itemFactory);

      when(server.getItemFactory()).thenReturn(itemFactory);

      ItemStack itemStack = mock(ItemStack.class);
      LeatherArmorMeta laMeta = mock(LeatherArmorMeta.class);

      Color armorColor = Color.fromARGB(1908001);
      when(laMeta.getColor()).thenReturn(armorColor);

      when(itemStack.getItemMeta()).thenReturn(laMeta);
      when(itemStack.getType()).thenReturn(Material.LEATHER_HORSE_ARMOR);

      when(horseInv.getArmor()).thenReturn(itemStack);

      EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
      Assert.assertTrue(Objects.nonNull(eggData.getArmorColor()));
    }
  }

  /**
   * {@link EggDataBase#getTagDataMap()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetTagDataMap() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getTagDataMap()));
  }

  /**
   * {@link EggDataBase#getHorseEggTagDataMap()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetHorseEggTagDataMap() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getHorseEggTagDataMap()));
  }

  /**
   * {@link EggDataBase#getIdNamespaceMap()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetIdNamespaceMap() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getIdNamespaceMap()));
  }

  /**
   * {@link EggDataBase#getEntityTagMap()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetEntityTagMap() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getEntityTagMap()));
  }

  /**
   * {@link EggDataBase#getDisplayMap()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetDisplayMap() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getDisplayMap()));
  }

  /**
   * {@link EggDataBase#getHorseEggTagDataList()} のためのテスト・メソッド.
   */
  @Test
  public final void testGetHorseEggTagDataList() {
    this.setUpHorse();

    EggDataBase eggData = EggDataFactory.newEggData(EntityType.HORSE, absHorse);
    Assert.assertTrue(Objects.nonNull(eggData.getHorseEggTagDataList()));
  }

}
