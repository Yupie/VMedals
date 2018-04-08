package com.vespamc.vmedals;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.vespamc.vmedals.utils.Chat;

public class MedalInventory {
	
	private Inventory medalInv;
	
	public MedalInventory() {
		Inventory inv = Main.instance.getServer().createInventory(null, 9, Chat.msg("&9Medals"));
		
		// Beacon Item
		List<String> beaconlore = new ArrayList<String>();
		beaconlore.add(Chat.msg("&cCraft 10 Beacons"));
		beaconlore.add(Chat.msg("&aReward: /recipe"));
		inv.setItem(
				0,
				setName(new ItemStack(Material.BEACON, 1),
						Chat.msg("&bBeacon Master"), beaconlore));
		
		// Ender Item
		List<String> enderlore = new ArrayList<String>();
		enderlore.add(Chat.msg("&cUse 100 000 ender pearls!"));
		enderlore.add(Chat.msg("&aReward: /enderchest"));
		inv.setItem(
				1,
				setName(new ItemStack(Material.ENDER_PEARL, 1),
						Chat.msg("&bTeleporter"), enderlore));
		
		// Workbench Item
		List<String> craftlore = new ArrayList<String>();
		craftlore.add(Chat.msg("&cCraft 75 000 times!"));
		craftlore.add(Chat.msg("&aReward: /workbench"));
		inv.setItem(
				2,
				setName(new ItemStack(Material.WORKBENCH, 1),
						Chat.msg("&bHandyman"), craftlore));
		
		// Fish Item
		List<String> fishlore = new ArrayList<String>();
		fishlore.add(Chat.msg("&cEat 500 pufferfish"));
		fishlore.add(Chat.msg("&aReward: 1000 Vespar"));
		inv.setItem(
				3,
				setName(new ItemStack(Material.RAW_FISH, 1, (short) 3),
						Chat.msg("&bHungry"), fishlore));
		
		// Bed Item
		List<String> sleeplore = new ArrayList<String>();
		sleeplore.add(Chat.msg("&cSleep 1500 times"));
		sleeplore.add(Chat.msg("&aReward: Set up to 2 homes!"));
		inv.setItem(
				4,
				setName(new ItemStack(Material.BED, 1), Chat.msg("&bLazy Dude"),
						sleeplore));
		
		// Sheep Item
		List<String> SheepLore = new ArrayList<String>();
		SheepLore.add(Chat.msg("&cShear 500 sheep!"));
		SheepLore.add(Chat.msg("&aReward: 2000 Vespar!"));
		inv.setItem(
				5,
				setName(new ItemStack(Material.WOOL, 1), Chat.msg("&bSheap"),
						SheepLore));
		
		// Apple Item
		List<String> AppleLore = new ArrayList<String>();
		AppleLore.add(Chat.msg("&cEat 40 golden apples!"));
		AppleLore.add(Chat.msg("&aReward: 64 Golden Blocks!"));
		inv.setItem(
				6,
				setName(new ItemStack(Material.GOLDEN_APPLE, 1), Chat.msg("&bUHC"),
						AppleLore));
		
		medalInv = inv;
	}
	
	public Inventory get(){
		return medalInv;
	}
	
	
	/*
	 * Gives an item stack a name and lore (each element of the lore list represents a new line)
	 */
	private ItemStack setName(ItemStack is, String name, List<String> lore) {
		ItemMeta im = is.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
			if (lore != null) {
				im.setLore(lore);
				is.setItemMeta(im);
			}

		}
		return is;

	}

}
