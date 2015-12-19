package com.mistphizzle.easycraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {
	
	EasyCraft plugin;
	
	public InventoryListener(EasyCraft plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		if (inventory.getName().equals(EasyCraft.easyInv.getName()) && clicked != null) {
			if (clicked.getType() == Material.TNT) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.tnt")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftTNT(player);
				return;
			}
			if (clicked.getType() == Material.HOPPER) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.hopper")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftHopper(player);
				return;
			}
			if (clicked.getType() == Material.CHEST) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.chest")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftChest(player);
				return;
			}
			
			if (clicked.getType() == Material.TRAPPED_CHEST) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.trappedchest")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftTrappedChest(player);
				return;
			}
			
			if (clicked.getType() == Material.BUCKET) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.bucket")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftBucket(player);
				return;
			}
			
			if (clicked.getType() == Material.GOLD_INGOT) {
				event.setCancelled(true);
				if (!player.hasPermission("easycraft.goldingot")) {
					player.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return;
				}
				player.closeInventory();
				Methods.craftGoldIngot(player);
				return;
			}
		}
	}
}
