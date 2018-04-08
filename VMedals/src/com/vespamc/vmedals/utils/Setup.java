package com.vespamc.vmedals.utils;

import com.vespamc.vmedals.MedalsCommand;
import com.vespamc.vmedals.listeners.BeaconCraft;
import com.vespamc.vmedals.listeners.BedEnter;
import com.vespamc.vmedals.listeners.EnderpearlThrow;
import com.vespamc.vmedals.listeners.FishEat;
import com.vespamc.vmedals.listeners.GappleEat;
import com.vespamc.vmedals.listeners.InventoryListener;
import com.vespamc.vmedals.listeners.ItemCraft;
import com.vespamc.vmedals.listeners.SheepShear;

public class Setup {
	
	public static void registerListeners(){
		new BeaconCraft();
		new BedEnter();
		new EnderpearlThrow();
		new FishEat();
		new GappleEat();
		new InventoryListener();
		new ItemCraft();
		new SheepShear();
	}
	
	public static void registerCommands(){
		new MedalsCommand();
	}
}
