package com.vespamc.vmedals.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class FishEat implements Listener{
	public FishEat() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e){
		Player p = e.getPlayer();

		DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");

		if (p.getGameMode() != GameMode.SURVIVAL)
			return;

		if (e.getItem().getType().equals(Material.RAW_FISH)) {
			ItemStack fish = e.getItem();

			if (fish.getDurability() == (byte) 3)
				if (!conf.getConfig().contains(Medal.PUFFERFISH_EATEN.toString())) {
					conf.getConfig().set(Medal.PUFFERFISH_EATEN.toString(), 1);
				} else {
					conf.getConfig().set(Medal.PUFFERFISH_EATEN.toString(),
							conf.getConfig().getInt(Medal.PUFFERFISH_EATEN.toString()) + 1);
				}
			conf.saveConfig();
			if (conf.getConfig().getInt(Medal.PUFFERFISH_EATEN.toString()) == 500) {
				Main.instance.getServer().dispatchCommand(
						Main.instance.getServer().getConsoleSender(),
						"money give " + p.getName() + " 1000");
				p.sendMessage(Chat
						.msg("Medals",
								"You have unlocked the &bHungry &7medal! You can now use /feed!!"));
			}
		}
	}
}
