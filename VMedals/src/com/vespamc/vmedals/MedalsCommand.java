package com.vespamc.vmedals;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.vespamc.vmedals.utils.Chat;
import com.vespamc.vmedals.utils.DataConfig;

public class MedalsCommand implements CommandExecutor {
	
	public MedalsCommand() {
		Main.instance.getCommand("medals").setExecutor(this);
		Main.instance.getCommand("medals").setAliases(Arrays.asList("med"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Chat.error("Medals",
					"Only ingame players can use this command!"));
			return false;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("medals")) {
			if (args.length == 0) {
				p.openInventory(Main.instance.getMedalInventory().get());
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("reset")) {
					if (!p.isOp()) {
						p.sendMessage(Chat
								.error("Medals",
										"You do not have enough permissions to use this!"));
						return false;
					}
					
					@SuppressWarnings("deprecation")
					OfflinePlayer op = Main.instance.getServer().getOfflinePlayer(args[1]);

					DataConfig conf = new DataConfig(Main.instance, op.getUniqueId().toString() + ".yml");
					if (!conf.getConfig().contains("Crafted")) {
						p.sendMessage(Chat.error("Medals",
								"That player has no data set!"));
					} else {
						for (String key : conf.getConfig().getKeys(false)) {
							conf.getConfig().set(key, null);
						}
						conf.saveConfig();
						p.sendMessage(Chat.msg("Medals",
								"You have reset the medal stats for &b"
										+ args[1]));
					}
				} else {
					p.sendMessage(Chat.msg("Medals",
							"Use /medals to see the medals menu!"));
				}
			} else if (args.length == 4) {
				if (args[0].equalsIgnoreCase("set")) {
					if (!p.isOp()) {
						p.sendMessage(Chat
								.error("Medals",
										"You do not have enough permissions to use this!"));
						return false;
					}

					if (Medal.valueOf(StringUtils.upperCase(args[1])) == null) {
						p.sendMessage(Chat
								.error("Medals",
										args[1] + " is not a valid Medal!\n&cValid Medals: \n&b" + Medal.values()));
						return false;
					}

					int x = 0;
					try {
						x = Integer.parseInt(args[3]);
					} catch (NumberFormatException e) {
						p.sendMessage(Chat.error("Medals", "" + args[3]
								+ " is not a valid number!"));
						return false;
					}

					@SuppressWarnings("deprecation")
					OfflinePlayer op = Main.instance.getServer().getOfflinePlayer(args[2]);

					DataConfig conf = new DataConfig(Main.instance, op.getUniqueId().toString() + ".yml");

					conf.getConfig().set(Medal.valueOf(args[1]).toString(), x);
					p.sendMessage(Chat.msg("Medals", "Successfully set &b"
							+ args[2] + "'s " + args[1] + " &7to&b " + x));
					conf.saveConfig();

				} else {
					p.sendMessage(Chat.msg("Medals",
							"Use /medals to see the medals menu!"));
				}
			} else {
				p.sendMessage(Chat.msg("Medals",
						"Use /medals to see the medals menu!"));
			}
		}
		return false;
	}

}
