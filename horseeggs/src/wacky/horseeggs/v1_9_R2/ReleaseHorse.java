package wacky.horseeggs.v1_9_R2;

import java.util.List;
import java.util.UUID;

import net.minecraft.server.v1_9_R2.EntityHorse;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftHorse;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;

public class ReleaseHorse {

	public ReleaseHorse(ItemStack item, Location loc) {
		String name = null;
		Double MaxHP = 0.0;
		Double HP = 0.0;
		Double speed = 0.0;
		Double jump = 0.0;
		OfflinePlayer owner = null;
		ItemStack saddle = null;
		ItemStack armor = null;
		Boolean chest = false;
		Variant variant = null;
		Color color = null;
		Style style = null;

		net.minecraft.server.v1_9_R2.ItemStack stack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound horseData = stack.getTag().getCompound("HorseEgg");
		if(!horseData.isEmpty()){//NBTから読むだけ簡単
			name = horseData.getString("Name");
			MaxHP = horseData.getDouble("MaxHealth");
			HP = horseData.getDouble("Health");
			speed = horseData.getDouble("Speed");
			jump = horseData.getDouble("Jump");

			if(horseData.getLong("UUIDMost") != 0){
				owner = Bukkit.getOfflinePlayer(new UUID(horseData.getLong("UUIDMost"), horseData.getLong("UUIDLeast")));
			}

			if(horseData.getBoolean("Saddle")) saddle = new ItemStack(Material.SADDLE);
			if(!horseData.getString("Armor").isEmpty()){
				armor = new ItemStack(Material.getMaterial(horseData.getString("Armor")));
			}
			chest = horseData.getBoolean("Chest");

			variant = Variant.valueOf(horseData.getString("Variant"));
			if(variant == Variant.HORSE){
				color = Color.valueOf(horseData.getString("Color"));
				style = Style.valueOf(horseData.getString("Style"));
			}

		}else{//Loreから読み取り

			name = item.getItemMeta().getDisplayName();
			List<String> list = item.getItemMeta().getLore();
			for(int i=0; i < list.size(); i++){//順番とか気にしなくて良くなる
				String str = list.get(i);

				if(str.startsWith("HP")){
					MaxHP = NumberConversions.toDouble(str.split(" ")[1].split("/")[1]);
					HP = NumberConversions.toDouble(str.split(" ")[1].split("/")[0]);

				}else if(str.startsWith("Speed")){
					speed = NumberConversions.toDouble(str.split(" ")[1]) /43;

				}else if(str.startsWith("Jump")){
					jump = NumberConversions.toDouble(str.split(" ")[1]);

				}else if(str.startsWith("Height")){//空白であってます

				}else if(str.startsWith("Owner")){
					owner = Bukkit.getOfflinePlayer(str.split(" ")[1]);

				}else if(str.startsWith("[")){//装備は必ず[]で囲む
					if (str.contains("SADDLE")) saddle = new ItemStack(Material.SADDLE);
					if (str.contains("IRON_BARDING")) armor = new ItemStack(Material.IRON_BARDING);
					else if (str.contains("GOLD_BARDING")) armor = new ItemStack(Material.GOLD_BARDING);
					else if (str.contains("DIAMOND_BARDING")) armor = new ItemStack(Material.DIAMOND_BARDING);
					chest = str.contains("CHEST");

				}else{//消去法 残ったのは馬の種類、色、模様
					if(str.contains("/")){
						variant = Variant.HORSE;
						color = Color.valueOf(str.split("/")[0]);
						style = Style.valueOf(str.split("/")[1]);
					}else{
						variant = Variant.valueOf(str);
					}
				}
			}

		}


		//馬生成をギリギリまで遅らせる
		Horse horse = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
		horse.setAge(6000);//繁殖待ち6000tick
		horse.setCustomName(name);
		horse.setMaxHealth(MaxHP);
		horse.setHealth(HP);

		//speedは書き込みもめんｄ
		NBTTagCompound tag = new NBTTagCompound();
		EntityHorse eh =((CraftHorse)horse).getHandle();
		eh.b(tag);
		NBTTagList attributes = tag.getList("Attributes", 10);
		for (int j=0; j<attributes.size(); j++) {
			NBTTagCompound attr = attributes.get(j);
			if (attr.getString("Name").equals("generic.movementSpeed")) {
				attr.setDouble("Base", speed);
				attributes.a(j, attr);
				break;
			}
		}
		tag.set("Attributes",attributes);
		eh.a(tag);

		horse.setJumpStrength(jump);
		horse.setOwner(owner);
		horse.setCarryingChest(chest);
		horse.getInventory().setSaddle(saddle);
		horse.getInventory().setArmor(armor);
		horse.setVariant(variant);
		if(variant == Variant.HORSE){
			horse.setColor(color);
			horse.setStyle(style);
		}else if(variant == Variant.SKELETON_HORSE){//骨馬は常時乗れるように
			horse.setTamed(true);
		}
	}
}
