package com.vespamc.vmedals.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class InventoryListener implements Listener{

	public InventoryListener() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if (e.getInventory() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().equals(Main.instance.getMedalInventory().get())) {
			DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BEACON) {
				if (!conf.getConfig().contains(Medal.BEACONS_CRAFTED.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never crafted a beacon!"));
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Amount of beacons crafted: "
									+ conf.getConfig().getInt(Medal.BEACONS_CRAFTED.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.ENDER_PEARL) {
				if (!conf.getConfig().contains(Medal.ENDERPEARLS_THROWN.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never thrown an enderpearl!"));
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Amount of enderpearls thrown: "
									+ conf.getConfig()
											.getInt(Medal.ENDERPEARLS_THROWN.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.WORKBENCH) {
				if (!conf.getConfig().contains(Medal.ITEMS_CRAFTED.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never crafted anything!"));
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Amount of items you have crafted: "
									+ conf.getConfig().getInt(Medal.ITEMS_CRAFTED.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.RAW_FISH) {
				if (!conf.getConfig().contains(Medal.PUFFERFISH_EATEN.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never eaten a pufferfish!"));
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Amount of pufferfish eaten: "
									+ conf.getConfig()
											.getInt(Medal.PUFFERFISH_EATEN.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.BED) {
				if (!conf.getConfig().contains(Medal.BEDS_ENTERED.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never slept!"));
				} else {
					p.sendMessage(Chat.msg("Medals", "Amount of times slept: "
							+ conf.getConfig().getInt(Medal.BEDS_ENTERED.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.WOOL) {
				if (!conf.getConfig().contains(Medal.SHEEP_SHEARED.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never sheared a sheep!"));
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Amount of times sheared: "
									+ conf.getConfig().getInt(Medal.SHEEP_SHEARED.toString())));
				}
			} else if (e.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
				if (!conf.getConfig().contains(Medal.GOLDEN_APPLES_EATEN.toString())) {
					p.sendMessage(Chat.error("Medals",
							"You have never eaten a golden apple!"));
				} else {
					p.sendMessage(Chat.msg(
							"Medals",
							"Amount of golden apples eaten: "
									+ conf.getConfig().getInt(
											Medal.GOLDEN_APPLES_EATEN.toString())));
				}
			}
			p.closeInventory();
		}
	}
}
