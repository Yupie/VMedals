package com.vespamc.vmedals.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class BeaconCraft implements Listener {

	public BeaconCraft() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e){
		Player p = (Player) e.getWhoClicked();

		if (p.getGameMode() != GameMode.SURVIVAL)
			return;

		DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");

		if (e.getCurrentItem().getType() == Material.BEACON) {
			if (!conf.getConfig().contains(Medal.BEACONS_CRAFTED.toString())) {
				conf.getConfig().set(Medal.BEACONS_CRAFTED.toString(), 1);
			} else {
				conf.getConfig().set(Medal.BEACONS_CRAFTED.toString(),
						conf.getConfig().getInt(Medal.BEACONS_CRAFTED.toString()) + 1);
			}
			conf.saveConfig();
			if (conf.getConfig().getInt(Medal.BEACONS_CRAFTED.toString()) == 10) {
				Main.instance.getServer().dispatchCommand(
						Main.instance.getServer().getConsoleSender(),
						"pex user " + p.getName() + " add essentials.recipe");
				p.sendMessage(Chat
						.msg("Medals",
								"You unlocked the &bBeacon Master &7medal! You can now use /recipe !!"));
			}
		}

	}
}
