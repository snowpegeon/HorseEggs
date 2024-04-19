package wacky.horseeggs.minecraftIO;

import com.github.teruteru128.logger.Logger;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftAbstractHorse;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;

import java.util.List;
import java.util.UUID;

public class ReleaseHorse {


	public ReleaseHorse(ItemStack item, Location loc, Logger _logger) {
		_logger.debug("new ReleaseHorse(ItemStack,Location,Logger):Start");
		_logger.trace("itemstack:" + item);
		_logger.trace("loc:" + loc);
		String name = null;
		Double MaxHP = 0.0;
		Double HP = 0.0;
		Double speed = 0.0;
		Double jump = 0.0;
		OfflinePlayer owner = null;
		ItemStack saddle = null;
		ItemStack armor = null;
		Boolean chest = false;
		Variant variant = null;//もはや別Entity.
		EntityType type = null;
		Color color = null;
		Llama.Color Lcolor = null;
		Style style = null;
		short decor = 0;
		int strength = 0;

		net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
		CompoundTag horseData = stack.getTag().getCompound("HorseEgg");
		if(!horseData.isEmpty()){//NBTから読むだけ簡単
			_logger.debug("horseData is not Empty");
			name = horseData.getString("Name");
			MaxHP = horseData.getDouble("MaxHealth");
			HP = horseData.getDouble("Health");
			speed = horseData.getDouble("Speed");
			jump = horseData.getDouble("Jump");
			_logger.trace("horseData.getString(\"Name\"):" + name);
			_logger.trace("horseData.getDouble(\"MaxHealth\"):" + MaxHP);
			_logger.trace("horseData.getDouble(\"Health\"):" + HP);
			_logger.trace("horseData.getDouble(\"Speed\"):" + speed);
			_logger.trace("horseData.getDouble(\"Jump\"):" + jump);

			if(horseData.getLong("UUIDMost") != 0){
				_logger.trace("horseData.getLong(\"UUIDMost\") != 0");
				owner = Bukkit.getOfflinePlayer(new UUID(horseData.getLong("UUIDMost"), horseData.getLong("UUIDLeast")));
			}

			if(horseData.getBoolean("Saddle")) {
				_logger.trace("has Saddle");
				saddle = new ItemStack(Material.SADDLE);
			}
			if(!horseData.getString("Armor").isEmpty()){//馬鎧またはラマのカーペット
				_logger.trace("has Armor");
				decor = horseData.getShort("Decor");//1.12までの仕様
				armor = new ItemStack(Material.getMaterial(horseData.getString("Armor")),1,decor);
			}
			chest = horseData.getBoolean("Chest");
			_logger.trace("horseData.getBoolean(\"Chest\"):" + chest);

			if(!horseData.getString("Type").isEmpty()){//旧バージョンだとなかったりする。
				_logger.trace("!horseData.getString(\"Type\").isEmpty()");
				type = EntityType.valueOf(horseData.getString("Type"));
			}else{
				_logger.trace("horseData.getString(\"Type\").isEmpty()");
				variant = Variant.valueOf(horseData.getString("Variant"));
				_logger.trace("Variant.valueOf(horseData.getString(\"Variant\"):"+variant);
				switch(variant){
				case HORSE:
					type = EntityType.HORSE;
					break;
				case DONKEY:
					type = EntityType.DONKEY;
					break;
				case MULE:
					type = EntityType.MULE;
					break;
				case UNDEAD_HORSE:
					type = EntityType.ZOMBIE_HORSE;
					break;
				case SKELETON_HORSE:
					type = EntityType.SKELETON_HORSE;
					break;
				case LLAMA:
					type = EntityType.LLAMA;
					break;
				default:
					type = EntityType.HORSE;
				}
			}
			_logger.trace("entity type:" + type);

			if(type == EntityType.HORSE){
				_logger.trace("type is HORSE");
				color = Color.valueOf(horseData.getString("Color"));
				style = Style.valueOf(horseData.getString("Style"));
			}else if(type == EntityType.LLAMA || type == EntityType.TRADER_LLAMA){
				_logger.trace("type is LLAMA OR TRADER_LLAMA");
				Lcolor = Llama.Color.valueOf(horseData.getString("Color"));
				strength = horseData.getInt("Strength");
			}

		}else{//Loreから読み取り
			_logger.debug("horseData is Empty");

			name = item.getItemMeta().getDisplayName();
			_logger.trace("item.getItemMeta().getDisplayName():" + name);
			List<String> list = item.getItemMeta().getLore();
			for(int i=0; i < list.size(); i++){//順番とか気にしなくて良くなる
				String str = list.get(i);
				_logger.trace("item.getItemMeta().getLore():" + str);

				if(str.startsWith("HP")){
					MaxHP = NumberConversions.toDouble(str.split(" ")[1].split("/")[1]);
					HP = NumberConversions.toDouble(str.split(" ")[1].split("/")[0]);
					_logger.trace("MaxHP:" + MaxHP);
					_logger.trace("HP:" + HP);
				}else if(str.startsWith("Speed")){
					speed = NumberConversions.toDouble(str.split(" ")[1]) /43;
					_logger.trace("Speed:" + speed);

				}else if(str.startsWith("Jump")){
					jump = NumberConversions.toDouble(str.split(" ")[1]);
					_logger.trace("jump:" + jump);

				}else if(str.startsWith("Height")){//空白であってます
					_logger.trace("Height(str):" + str);

				}else if(str.startsWith("Owner")){
					owner = Bukkit.getOfflinePlayer(str.split(" ")[1]);
					_logger.trace("jump:" + owner);

				}else if(str.startsWith("[")){//装備は必ず[]で囲む
					if (str.contains("SADDLE")) {
						_logger.trace("str has SADDLE");
						saddle = new ItemStack(Material.SADDLE);
					}
					if (str.contains("IRON_HORSE_ARMOR") || str.contains("IRON_BARDING")) {
						_logger.trace("str has IRON_HORSE_ARMOR OR IRON_BARDING");
						armor = new ItemStack(Material.IRON_HORSE_ARMOR);
					}
					else if (str.contains("GOLDEN_HORSE_ARMOR") ||str.contains("GOLD_BARDING")) {
						_logger.trace("str has GOLDEN_HORSE_ARMOR OR GOLD_BARDING");
						armor = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
					}
					else if (str.contains("DIAMOND_HORSE_ARMOR") ||str.contains("DIAMOND_BARDING")) {
						_logger.trace("str has DIAMOND_HORSE_ARMOR OR DIAMOND_BARDING");
						armor = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
					}
					chest = str.contains("CHEST");
					_logger.trace("str has CHEST:"+chest);

				}else{//消去法 残ったのは馬の種類、色、模様
					_logger.trace("str.contains(\"/\"):"+str.contains("/"));
					if(str.contains("/")){
						variant = Variant.HORSE;
						color = Color.valueOf(str.split("/")[0]);
						style = Style.valueOf(str.split("/")[1]);
						_logger.trace("variant:"+variant);
						_logger.trace("color:"+color);
						_logger.trace("style:"+style);
					}else{
						variant = Variant.valueOf(str);
						_logger.trace("variant:"+variant);
					}
				}
			}

		}


		//馬生成をギリギリまで遅らせる 1.12では馬、ロバ、骨馬は全て別種
		AbstractHorse horse = (AbstractHorse) loc.getWorld().spawnEntity(loc, type);

		//speedは書き込みもめんｄ
		CompoundTag tag = new CompoundTag();
		net.minecraft.world.entity.animal.horse.AbstractHorse eh =((CraftAbstractHorse)horse).getHandle();

		//一旦普通の馬のNBTコピー
		eh.addAdditionalSaveData(tag);
		ListTag attributes = tag.getList("Attributes", 10);
		for (int j=0; j<attributes.size(); j++) {
			CompoundTag attr = (CompoundTag) attributes.get(j);
			_logger.trace("attr.getString(\"Name\"):"+attr.getString("Name"));
			if (attr.getString("Name").equals("minecraft:generic.movement_speed")) {
				attr.putDouble("Base", speed);
				attributes.set(j, attr);
				break;
			}
		}
		tag.put("Attributes",attributes);
		eh.readAdditionalSaveData(tag);//速度書き込んでペースト

 		horse.setAge(6000);//繁殖待ち6000tick
		horse.setCustomName(name);
		horse.setMaxHealth(MaxHP);
		horse.setHealth(HP);

		horse.setJumpStrength(jump);
		horse.setOwner(owner);
		horse.getInventory().setSaddle(saddle);
		if(horse.getType() == EntityType.HORSE){
			_logger.trace("horse.getType() == EntityType.HORSE");
			((Horse) horse).setColor(color);
			((Horse) horse).setStyle(style);
			((Horse) horse).getInventory().setArmor(armor);
		}else if(horse.getType() == EntityType.SKELETON_HORSE){//骨馬は常時乗れるように
			_logger.trace("horse.getType() == EntityType.SKELETON_HORSE");
			horse.setTamed(true);
		}else if(horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA){//凝ってるなぁorz
			_logger.trace("horse.getType() == EntityType.LLAMA || horse.getType() == EntityType.TRADER_LLAMA");
			((Llama) horse).setColor(Lcolor);
			((Llama) horse).setStrength(strength);
			((Llama) horse).getInventory().setDecor(armor);
			((ChestedHorse) horse).setCarryingChest(chest);
		}else if(horse instanceof ChestedHorse){
			_logger.trace("horse instanceof ChestedHorse");
			((ChestedHorse) horse).setCarryingChest(chest);
		}
	}
}
