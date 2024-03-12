package wacky.horseeggs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.DaylightDetector;
import org.bukkit.block.Dispenser;
import org.bukkit.block.EnderChest;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.block.Jukebox;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.*;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import wacky.horseeggs.minecraftIO.PlayerInteractListener;



public class HorseEggs extends JavaPlugin implements Listener{

	public FileConfiguration config;

	@Override
	public void onEnable() {

		config = this.getConfig();
		config.options().copyDefaults(true);
		config.options().header("HorseEggs Configuration");
		this.saveConfig();

		ShapedRecipe storageSignRecipe = new ShapedRecipe(emptyHorseEgg(1));
		storageSignRecipe.shape(" P ","PEP"," P ");
		storageSignRecipe.setIngredient('P', Material.ENDER_PEARL);
		storageSignRecipe.setIngredient('E', Material.EGG);
		getServer().addRecipe(storageSignRecipe);

		getServer().getPluginManager().registerEvents(this, this);

		new PlayerInteractListener(this);

		//new ItemDespawnListener(this);.
	}

	@Override
	public void onDisable(){}

	@EventHandler
	public void onBlockDispense(BlockDispenseEvent event){
		if(event.isCancelled() || event.getBlock().getType() == Material.DROPPER) return;
		if(isHorseEgg(event.getItem()) || isEmptyHorseEgg(event.getItem())){
			event.setCancelled(true);//仕様変更用にキャンセルだけ.凍結中
			/*
			Dispenser dispenserM = (Dispenser) event.getBlock().getState().getData();
			Location loc = event.getBlock().getRelative(dispenserM.getFacing()).getLocation();
			loc.add(0.5, 0.2, 0.5);
			releaseHorse(event.getItem(),loc);
			org.bukkit.block.Dispenser dispenserS = (org.bukkit.block.Dispenser)event.getBlock().getState();
			dispenserS.getInventory().remove(event.getItem());
			*/
		}
	}

	//定義があるのは空だけ
	public ItemStack emptyHorseEgg(int i){
		ItemStack egg = new ItemStack(Material.GHAST_SPAWN_EGG, i);
		ItemMeta meta = egg.getItemMeta();
		meta.setDisplayName("HorseEgg");
		List<String> lore = new ArrayList<String>();
		lore.add("Empty");
		meta.setLore(lore);
		egg.setItemMeta(meta);
		return egg;
	}

	public boolean isEmptyHorseEgg(ItemStack item){//1.13では白い馬卵が無い
		if(item.getType() == Material.GHAST_SPAWN_EGG || item.getType() == Material.PIG_SPAWN_EGG && item.getItemMeta().hasLore()){
			if(item.getItemMeta().getLore().get(0).equals("Empty")) return true;
		}
		return false;
	}

	public boolean isHorseEgg(ItemStack item){//1.8まではダメージ値100、1.9ではメタ内にエンティティ記載あり
		if(item.getType() == Material.HORSE_SPAWN_EGG || item.getType() == Material.ZOMBIE_HORSE_SPAWN_EGG || item.getType() == Material.SKELETON_HORSE_SPAWN_EGG || item.getType() == Material.DONKEY_SPAWN_EGG ||item.getType() == Material.MULE_SPAWN_EGG || item.getType() == Material.LLAMA_SPAWN_EGG){
			if(item.getItemMeta().hasLore() && item.getItemMeta().getLore().size() >= 3) return true;
		}
		return false;
	}

	public boolean isClickable(Block block) {//名前変わりすぎ

		if( block.getType().equals(Material.IRON_DOOR) ||
				block.getType().equals(Material.IRON_TRAPDOOR)){
			// 鉄シリーズは問答無用でfalse
			return false;
		} else if(block.getState() instanceof Sign){
			// 看板は編集可・不可の状態が変化するので、動的に取得する
			return !((Sign)block.getState()).isWaxed();
		} else if(
			// それ以外
			// ベルは触った場所が本体以外だとうまく動作しないが、場所を知る手立てがないため入れてない
			// コンポスターは最大状態以外だとうまく動作しないが、知る手立てがないため入れていない
			// エンティティ分類であるトロッコ系は現状止める手立てはないが、とりあえず入れてある
				block.getState() instanceof Container ||
				block.getState() instanceof EnderChest ||
				block.getState() instanceof EnchantingTable ||
				block.getState() instanceof CommandBlock ||
				block.getState() instanceof DaylightDetector ||
				block.getState() instanceof Jukebox ||
				block.getState() instanceof ChiseledBookshelf ||
				block.getBlockData() instanceof Door ||
				block.getBlockData() instanceof TrapDoor ||
				block.getBlockData() instanceof Bed ||
				block.getBlockData() instanceof Gate ||
				block.getBlockData() instanceof Cake ||
				block.getBlockData() instanceof Switch ||
				block.getBlockData() instanceof Repeater ||
				block.getBlockData() instanceof Dispenser ||
				block.getBlockData() instanceof Comparator ||
				block.getBlockData() instanceof Hopper ||
				block.getBlockData() instanceof NoteBlock ||
				block.getBlockData() instanceof Chest ||
				block.getBlockData() instanceof Grindstone ||
				block.getBlockData() instanceof Furnace ||
				block.getBlockData() instanceof Barrel ||
				block.getBlockData().getMaterial().equals(Material.CRAFTING_TABLE) ||
				block.getBlockData().getMaterial().equals(Material.ANVIL) ||
				block.getBlockData().getMaterial().equals(Material.CHIPPED_ANVIL) ||
				block.getBlockData().getMaterial().equals(Material.DAMAGED_ANVIL) ||
				block.getBlockData().getMaterial().equals(Material.BEACON) ||
				block.getBlockData().getMaterial().equals(Material.BREWING_STAND) ||
				block.getBlockData().getMaterial().equals(Material.FURNACE_MINECART) ||
				block.getBlockData().getMaterial().equals(Material.HOPPER_MINECART) ||
				block.getBlockData().getMaterial().equals(Material.CAKE) ||
				block.getBlockData().getMaterial().equals(Material.CANDLE_CAKE) ||
				block.getBlockData().getMaterial().equals(Material.CHEST_MINECART) ||
				block.getBlockData().getMaterial().equals(Material.COMMAND_BLOCK) ||
				block.getBlockData().getMaterial().equals(Material.DAYLIGHT_DETECTOR) ||
				block.getBlockData().getMaterial().equals(Material.RESPAWN_ANCHOR) ||
				block.getBlockData().getMaterial().equals(Material.STONECUTTER) ||
				block.getBlockData().getMaterial().equals(Material.CARTOGRAPHY_TABLE) ||
				block.getBlockData().getMaterial().equals(Material.SMITHING_TABLE) ||
				block.getBlockData().getMaterial().equals(Material.LOOM) ||
				block.getBlockData().getMaterial().equals(Material.SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.RED_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.ORANGE_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.YELLOW_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.LIME_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.GREEN_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.CYAN_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.BLUE_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.PURPLE_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.MAGENTA_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.LIGHT_BLUE_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.PINK_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.BROWN_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.WHITE_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.GRAY_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.LIGHT_GRAY_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.BLACK_SHULKER_BOX) ||
				block.getBlockData().getMaterial().equals(Material.ANVIL)
		) {
			return true;
		}
		return false;
	}

}
