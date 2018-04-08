package com.vespamc.vmedals.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class SheepShear implements Listener {

	public SheepShear() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onShear(PlayerShearEntityEvent e){
		if (e.getEntity().getType() == EntityType.SHEEP) {
			Player p = (Player) e.getPlayer();

			if (p.getGameMode() != GameMode.SURVIVAL)
				return;

			DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");

			if (!conf.getConfig().contains(Medal.SHEEP_SHEARED.toString())) {
				conf.getConfig().set(Medal.SHEEP_SHEARED.toString(), 1);
			} else {
				conf.getConfig().set(Medal.SHEEP_SHEARED.toString(),
						conf.getConfig().getInt(Medal.SHEEP_SHEARED.toString()) + 1);
			}
			conf.saveConfig();
			if (conf.getConfig().getInt(Medal.SHEEP_SHEARED.toString()) == 5000) {
				Main.instance.getServer().dispatchCommand(
						Main.instance.getServer().getConsoleSender(),
						"money give " + p.getName() + " 2000");
				p.sendMessage(Chat
						.msg("Medals",
								"You unlocked the &bSheap &7medal! You got 5000 Vespar!!"));
			}

		}
	}
}
