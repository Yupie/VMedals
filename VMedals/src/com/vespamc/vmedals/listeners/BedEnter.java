package com.vespamc.vmedals.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class BedEnter implements Listener {

	public BedEnter() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e){
		Player p = e.getPlayer();

		DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");

		if (p.getGameMode() != GameMode.SURVIVAL)
			return;

		if (!conf.getConfig().contains(Medal.BEDS_ENTERED.toString())) {
			conf.getConfig().set(Medal.BEDS_ENTERED.toString(), 1);
		} else {
			conf.getConfig().set(Medal.BEDS_ENTERED.toString(),
					conf.getConfig().getInt(Medal.BEDS_ENTERED.toString()) + 1);
		}

		conf.saveConfig();

		if (conf.getConfig().getInt(Medal.BEDS_ENTERED.toString()) == 1500) {
			Main.instance.getServer().dispatchCommand(
					Main.instance.getServer().getConsoleSender(),
					"pex user " + p.getName()
							+ " add essentials.sethome.multiple.vip");
			p.sendMessage(Chat
					.msg("Medals",
							"You have unlocked the &bLazyDude &7medal! You can now set up to 2 homes! /sethome <name>"));
		}
	}
}
