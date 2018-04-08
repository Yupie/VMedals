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

public class GappleEat implements Listener {

	public GappleEat() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e){
		Player p = e.getPlayer();

		DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");

		if (p.getGameMode() != GameMode.SURVIVAL)
			return;
		if (e.getItem().getType().equals(Material.GOLDEN_APPLE)) {
			if (!conf.getConfig().contains(Medal.GOLDEN_APPLES_EATEN.toString())) {
				conf.getConfig().set(Medal.GOLDEN_APPLES_EATEN.toString(), 1);
			} else {
				conf.getConfig().set(Medal.GOLDEN_APPLES_EATEN.toString(),
						conf.getConfig().getInt(Medal.GOLDEN_APPLES_EATEN.toString()) + 1);
			}
			conf.saveConfig();
			if (conf.getConfig().getInt(Medal.GOLDEN_APPLES_EATEN.toString()) == 40) {
				p.getInventory()
						.addItem(new ItemStack(Material.GOLD_BLOCK, 64));
				p.sendMessage(Chat
						.msg("Medals",
								"You have unlocked the &bUHC &7medal! You got 64 gold blocks!!!"));
			}
		}
	}
}
