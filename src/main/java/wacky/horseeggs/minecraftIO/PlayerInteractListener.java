package wacky.horseeggs.minecraftIO;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftAbstractHorse;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import wacky.horseeggs.HorseEggs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInteractListener implements Listener{

	private HorseEggs plugin;

	public PlayerInteractListener(HorseEggs plugin){
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);//a
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){//このイベントは2回発生する
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		PlayerInventory inv = player.getInventory();
		Entity entity = event.getRightClicked();
		AbstractHorse horse;
		ItemStack itemInHand = player.getInventory().getItemInMainHand();

		if(plugin.isHorseEgg(itemInHand)){//馬卵を他のエンティティ、馬に使ったとき
			event.setCancelled(true);//子馬が生まれないように
			if(event.getHand() == EquipmentSlot.OFF_HAND) return;//オフハンド用の判定は拒否、収納→即放出と増殖がある
			if(!player.hasPermission("horseeggs.release")) return;
			Location loc = event.getRightClicked().getLocation();
			new ReleaseHorse(itemInHand, loc);
			int amount = itemInHand.getAmount();
			if(plugin.config.getBoolean("single-use")){
				if(amount == 1) inv.setItemInMainHand(null);
				else itemInHand.setAmount(amount -1);
			}else if(itemInHand.getAmount() == 1) inv.setItemInMainHand(plugin.emptyHorseEgg(1));
			else{
				itemInHand.setAmount(amount -1);
				inv.setItemInMainHand(itemInHand);
				if(inv.firstEmpty() == -1 || inv.firstEmpty() >= 36){
					loc.add(0, 0.5, 0);
					entity.getWorld().dropItem(loc, plugin.emptyHorseEgg(1));
				}else{
					player.getInventory().addItem(plugin.emptyHorseEgg(1));
				}
			}
			return;

			//何かが乗ってる時は回収不能、子馬・出した直後もNG
		}else if(plugin.isEmptyHorseEgg(itemInHand)){
			event.setCancelled(true);//馬に卵を使ったことになるんだとか
			if(event.getHand() == EquipmentSlot.OFF_HAND) return;//オフハンド用の判定は拒否
			if(entity instanceof AbstractHorse){//馬系
				horse = (AbstractHorse)entity;
			}else{
				return;
			}
			if(horse.isAdult() && horse.getPassengers().isEmpty() && horse.getAge() < 5980){

				if(!player.hasPermission("horseeggs.capture")) return;

				EntityType type = horse.getType();
				ItemStack horseegg = null;
				switch(type){
				case HORSE:
					horseegg = new ItemStack(Material.HORSE_SPAWN_EGG, 1);//見た目を馬卵にする方法
					break;
				case SKELETON_HORSE:
					horseegg = new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG, 1);//骨馬卵
					break;
				case ZOMBIE_HORSE:
					horseegg = new ItemStack(Material.ZOMBIE_HORSE_SPAWN_EGG, 1);//腐馬卵
					break;
				case DONKEY:
					horseegg = new ItemStack(Material.DONKEY_SPAWN_EGG, 1);//ロバ卵
					break;
				case MULE:
					horseegg = new ItemStack(Material.MULE_SPAWN_EGG, 1);//騾馬卵
					break;
				case LLAMA:
					horseegg = new ItemStack(Material.LLAMA_SPAWN_EGG, 1);//ラマ卵
					break;
				case TRADER_LLAMA:
					horseegg = new ItemStack(Material.TRADER_LLAMA_SPAWN_EGG, 1);
					break;
				default:
					return;
				}


				CompoundTag tag = new CompoundTag();
				net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(horseegg);
				CompoundTag id = new CompoundTag();
				id.putString("id", "minecraft:" + type.toString().toLowerCase());
				tag.put("EntityTag", id);
				CompoundTag horseData = new CompoundTag();
				List<String> list = new ArrayList<String>();

				//名前
				if(horse.getCustomName() != null) horseData.putString("Name", horse.getCustomName());
				//体力
				horseData.putDouble("Health",horse.getHealth());
				horseData.putDouble("MaxHealth", horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				list.add("HP: " + (int)horse.getHealth() +"/"+ (int)horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

				//速度、43倍すると実際の速度に
                CompoundTag tag2 = new CompoundTag();
                ((CraftAbstractHorse)horse).getHandle().addAdditionalSaveData(tag2);
				ListTag attributes = tag2.getList("Attributes", 10);
				for (int i=0; i<attributes.size(); i++) {
                    CompoundTag attr = (CompoundTag) attributes.get(i);
					if (attr.getString("Name").equals("minecraft:generic.movement_speed")) {
						Double speed = attr.getDouble("Base");
						horseData.putDouble("Speed", speed);
						if(Double.toString(speed*43).length() > 6) list.add("Speed: " + Double.toString(speed*43).substring(0, 6));
						else list.add("Speed: " + Double.toString(speed*43));
					}
				}

				//跳躍力、NBTにのみ書かれるべき
				Double jump = horse.getJumpStrength();
				horseData.putDouble("Jump", jump);

				double jumpHeight = 0;//from Zyin's HUD、ジャンプ高度
				while (jump > 0)
				{
					jumpHeight += jump;
					jump -= 0.08;
					jump *= 0.98;
				}
				if(Double.toString(jumpHeight).length() > 5) list.add("Height: " + Double.toString(jumpHeight).substring(0, 5));
				else list.add("Height: " + Double.toString(jumpHeight));


				horseData.putString("Type", horse.getType().toString());
				// TODO getVariantが非推奨型のため、取得方式を検討する。
				horseData.putString("Variant", horse.getVariant().toString());

				if(horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA){
					horseData.putInt("Strength", ((Llama) horse).getStrength());
					list.add("Strength: " + ((Llama) horse).getStrength());

					horseData.putString("Color", ((Llama) horse).getColor().toString());
					list.add(((Llama) horse).getColor().toString());
				}
				else if(horse.getType() == EntityType.HORSE){//馬
					horseData.putString("Color", ((Horse) horse).getColor().toString());
					horseData.putString("Style", ((Horse) horse).getStyle().toString());
					list.add(((Horse) horse).getColor().toString() + "/" + ((Horse) horse).getStyle().toString());

				}

				Location loc = horse.getLocation();
				loc.add(0, 0.5, 0);
				if(horse.isTamed()){//飼いならした人、UUIDを内部的には使用する。
					if(horse.getOwner() != null){
						AnimalTamer owner = horse.getOwner();
						horseData.putLong("UUIDMost", owner.getUniqueId().getMostSignificantBits());
						horseData.putLong("UUIDLeast", owner.getUniqueId().getLeastSignificantBits());
						list.add("Owner: " + owner.getName());
					}

					AbstractHorseInventory hInv = horse.getInventory();
					//サドル
					horseData.putBoolean("Saddle",hInv.getSaddle() != null);
					String str1 = hInv.getSaddle() == null ? "" : "[SADDLE]";

					String str2 = "";
					if(type == EntityType.HORSE && ((HorseInventory) hInv).getArmor() != null){
						horseData.putString("Armor", ((HorseInventory) hInv).getArmor().getType().toString());
						str2 = "[" + ((HorseInventory) hInv).getArmor().getType().toString() + "]";
					}else if((type == EntityType.LLAMA || type == EntityType.TRADER_LLAMA) && ((LlamaInventory) hInv).getDecor() != null){
						horseData.putString("Armor", ((LlamaInventory) hInv).getDecor().getType().toString());
						str2 = "[" + ((LlamaInventory) hInv).getDecor().getType().toString() + "]";
					}
					if(entity instanceof ChestedHorse){
						horseData.putBoolean("Chest", ((ChestedHorse) horse).isCarryingChest());
						if(((ChestedHorse) horse).isCarryingChest()) str2 = str2 + "[CHEST]";//ラマがカーペットとチェスト両持ちできる
					}else{
						horseData.putBoolean("Chest", false);
					}

					if((str1 + str2).length() > 0) list.add(str1 + str2);

					for(int i = 2; i <hInv.getSize();i++){//チェストと鎧?を除く
						if(hInv.getItem(i) == null) continue;
						horse.getWorld().dropItem(loc, hInv.getItem(i));
					}
				}

				tag.put("HorseEgg",horseData);
                stack.setTag(tag);
				horseegg = CraftItemStack.asBukkitCopy(stack);
				ItemMeta meta = horseegg.getItemMeta();
				meta.setDisplayName(horse.getCustomName());
				meta.setLore(list);
				horseegg.setItemMeta(meta);

				if(inv.getItemInMainHand().getAmount() == 1) inv.setItemInMainHand(horseegg);
				else{
					itemInHand.setAmount(itemInHand.getAmount() -1);
					if(inv.firstEmpty() == -1 || inv.firstEmpty() >= 36){
						horse.getWorld().dropItem(loc, horseegg);
					}else inv.addItem(horseegg);
				}
				horse.remove();
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		PlayerInventory inv = player.getInventory();
		ItemStack item = event.getItem();
		if(item == null) return;
		if(plugin.isEmptyHorseEgg(item)) {
			event.setCancelled(true);
			return;
		}else if(plugin.isHorseEgg(item)){
			event.setUseItemInHand(Result.DENY);
			if(event.isCancelled()){//まさかの水源、溶岩源を右クリックした場合、cancelledのはずだけどスポーンエッグは使える
				return;
			}
			if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
			if(!player.isSneaking()  && plugin.isClickable(event.getClickedBlock())) return;//ブロック優先
			event.setUseInteractedBlock(Result.DENY);
			if(!player.hasPermission("horseeggs.release")){
				event.setCancelled(true);
				return;
			}

			//馬がめり込まないようにしたい
			Block centerBlock = event.getClickedBlock().getRelative(event.getBlockFace());
			Location loc = centerBlock.getLocation();
			Boolean[][] blocks = new Boolean[5][5];
			Arrays.fill(blocks[0], false);
			Arrays.fill(blocks[4], false);
			boolean canSpawnCenter = true;
			for(int i = 1; i <= 3; i++){
				Arrays.fill(blocks[i], false);
				for(int j = 1; j <= 3; j++){
					blocks[i][j] = isSuffocating(centerBlock.getRelative(i-2, 1, j-2).getType());
					if(blocks[i][j]) canSpawnCenter = false;
				}
			}
			if(canSpawnCenter){
				loc.add(0.5, 0, 0.5);
				new ReleaseHorse(item, loc);
			}
			else search:{//どっかにブロック有り。
				for(int i = 0; i < 3; i++){
					for(int j = 0; j < 3; j++){//周囲9マス()にブロックが無いか
						Boolean canSpawn = !blocks[i][j] && !blocks[i][j+1] && !blocks[i][j+2] && !blocks[i+1][j] && !blocks[i+1][j+1] && !blocks[i+1][j+2] && !blocks[i+2][j] && !blocks[i+2][j+1] && !blocks[i+2][j+2];
						if(canSpawn){
							loc.add(i*0.5, 0, j*0.5);
							new ReleaseHorse(item, loc);
							break search;
						}
					}
				}//スポーン失敗。
				event.setCancelled(true);
				return;
			}

			//オフハンド対策
			int amount = item.getAmount();
			if(plugin.config.getBoolean("single-use")){
				if(amount == 1){
					if(event.getHand() == EquipmentSlot.HAND) inv.setItemInMainHand(null);
					else inv.setItemInOffHand(null);
				}
				else item.setAmount(amount -1);
			}else if(item.getAmount() == 1){
				if(event.getHand() == EquipmentSlot.HAND) inv.setItemInMainHand(plugin.emptyHorseEgg(1));
				else inv.setItemInOffHand(plugin.emptyHorseEgg(1));
			}
			else{
				item.setAmount(amount -1);
				if(inv.firstEmpty() == -1 || inv.firstEmpty() >= 36){
					loc.add(0, 0.5, 0);
					loc.getWorld().dropItem(loc, plugin.emptyHorseEgg(1));
				}else{
					inv.addItem(plugin.emptyHorseEgg(1));
				}
			}
		}
	}

	private boolean isSuffocating(Material mat){
		if(mat.isOccluding()) return true;//窒息する透過ブロック
		else if(mat == Material.TNT || mat == Material.ICE || mat == Material.GLOWSTONE || mat == Material.REDSTONE_BLOCK || mat == Material.SEA_LANTERN || mat == Material.SHROOMLIGHT || mat == Material.REDSTONE_LAMP || mat == Material.OCHRE_FROGLIGHT || mat == Material.PEARLESCENT_FROGLIGHT || mat == Material.VERDANT_FROGLIGHT){
			return true;
		}
		return false;
	}
}
