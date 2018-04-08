/**
 * @author Yupie
 */

package com.vespamc.vmedals;

import org.bukkit.plugin.java.JavaPlugin;

import com.vespamc.vmedals.utils.Setup;

public class Main extends JavaPlugin {
	
	public static Main instance;
	private MedalInventory medalInventory;

	@Override
	public void onEnable() {
		instance = this;
		setMedalInventory(new MedalInventory());
		Setup.registerCommands();
		Setup.registerListeners();
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}

	public MedalInventory getMedalInventory() {
		return medalInventory;
	}

	public void setMedalInventory(MedalInventory medalInventory) {
		this.medalInventory = medalInventory;
	}
}