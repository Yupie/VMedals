package com.vespamc.vmedals.utils;

import net.md_5.bungee.api.ChatColor;

public class Chat {
	
	public static String msg(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static String msg(String prefix, String msg){
		String s = "&8[&6" + prefix + "&8] &7" + msg;
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static String error(String prefix, String msg){
		String s = "&8[&6" + prefix + "&8] &c" + msg;
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
