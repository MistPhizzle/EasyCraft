package com.mistphizzle.easycraft;

import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class EasyCraft extends JavaPlugin {

	public static Plugin plugin;
	public static Logger log;
	
	public static Inventory easyInv;
	
	public void onEnable() {
		plugin = this;
		EasyCraft.log = this.getLogger();
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		new Commands(this);
		
		Methods.createInventory();
		
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
	}
}
