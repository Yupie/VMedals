package com.vespamc.vmedals.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import com.vespamc.vmedals.Main;
import com.vespamc.vmedals.Medal;
import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class EnderpearlThrow implements Listener{

	public EnderpearlThrow() {
		Main.instance.getServer().getPluginManager().registerEvents(this, Main.instance);
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e){
		if (!(e.getEntity().getShooter() instanceof Player))
			return;

		Player p = (Player) e.getEntity().getShooter();
		if (p.getGameMode() != GameMode.SURVIVAL)
			return;

		DataConfig conf = new DataConfig(Main.instance, p.getUniqueId().toString() + ".yml");
		if (e.getEntity() instanceof EnderPearl) {
			if (!conf.getConfig().contains(Medal.ENDERPEARLS_THROWN.toString())) {
				conf.getConfig().set(Medal.ENDERPEARLS_THROWN.toString(), 1);
			} else {
				conf.getConfig().set(Medal.ENDERPEARLS_THROWN.toString(),
						conf.getConfig().getInt(Medal.ENDERPEARLS_THROWN.toString()) + 1);
			}

			conf.saveConfig();
			if (conf.getConfig().getInt(Medal.ENDERPEARLS_THROWN.toString()) == 100000) {
				Main.instance.getServer().dispatchCommand(
						Main.instance.getServer().getConsoleSender(),
						"pex user " + p.getName()
								+ " add essentials.enderchest");
				p.sendMessage(Chat
						.msg("Medals",
								"You have unlocked the &bTeleporter &7medal! You can now use /enderchest!!"));
			}
		}
	}
}
