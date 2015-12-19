package com.mistphizzle.easycraft;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Methods {
	
	public static List<Block> getBlocksAroundPoint(Location location, double radius) {
		List<Block> blocks = new ArrayList<Block>();

		int xorg = location.getBlockX();
		int yorg = location.getBlockY();
		int zorg = location.getBlockZ();

		int r = (int) radius * 4;

		for (int x = xorg - r; x <= xorg + r; x++) {
			for (int y = yorg - r; y <= yorg + r; y++) {
				for (int z = zorg - r; z <= zorg + r; z++) {
					Block block = location.getWorld().getBlockAt(x, y, z);
					if (block.getLocation().distance(location) <= radius) {
						blocks.add(block);
					}
				}
			}
		}
		return blocks;
	}
	
	public static void createInventory() {
		EasyCraft.easyInv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "EasyCraft Crafting GUI");
		EasyCraft.easyInv.setItem(0, new ItemStack(Material.TNT, 1));
		EasyCraft.easyInv.setItem(1, new ItemStack(Material.HOPPER, 1));
		EasyCraft.easyInv.setItem(2, new ItemStack(Material.CHEST, 1));
		EasyCraft.easyInv.setItem(3, new ItemStack(Material.TRAPPED_CHEST, 1));
		EasyCraft.easyInv.setItem(4, new ItemStack(Material.BUCKET, 1));
		EasyCraft.easyInv.setItem(5, new ItemStack(Material.GOLD_INGOT, 1));
	}
	
	public static void craftTrappedChest(Player player) {
		int requiredChest = 1;
		int requiredHook = 1;
		
		int actualChest = getNumberOfMaterialInInventory(player, Material.CHEST);
		int actualHook = getNumberOfMaterialInInventory(player, Material.TRIPWIRE_HOOK);
		
		if (actualChest < requiredChest || actualHook < requiredHook) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft a trapped chest. You need at least 1 chest and 1 tripwire hook.");
			return;
		}
		
		double numberToCraft = 0;
		if ((actualChest / requiredChest) < (actualHook / requiredHook)) {
			numberToCraft = Math.floor(actualChest / requiredChest);
		} else {
			numberToCraft = Math.floor(actualHook / requiredHook);
		}
		
		int finalChest = (int) numberToCraft * requiredChest;
		int finalHook = (int) numberToCraft * requiredHook;
		
		ItemStack finalCraft = new ItemStack(Material.TRAPPED_CHEST, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.CHEST, finalChest));
		player.getInventory().removeItem(new ItemStack(Material.TRIPWIRE_HOOK, finalHook));
		return;
	}
	
	public static void craftGoldIngot(Player player) {
		int requiredGold = 9;
		int actualGold = getNumberOfMaterialInInventory(player, Material.GOLD_NUGGET);
		if (requiredGold < actualGold) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft a gold ingot. You need at least 9 gold nuggets.");
			return;
		}
		
		double numberToCraft = Math.floor(actualGold / requiredGold);
		double totalGold = requiredGold * numberToCraft;
		
		ItemStack finalCraft = new ItemStack(Material.GOLD_INGOT, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.GOLD_NUGGET, (int) totalGold));
		player.sendMessage(ChatColor.GREEN + "Successfully crafted " + (int) numberToCraft + " gold ingots, using " + totalGold + " gold nuggets.");
	}
	
	public static void craftChest(Player player) {
		int requiredWood = 8;
		int actualWood = getNumberOfMaterialInInventory(player, Material.WOOD);
		if (requiredWood < actualWood) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft a chest. You need at least 8 wooden planks.");
			return;
		}
		
		double numberToCraft = Math.floor(actualWood / requiredWood);
		double totalWood = requiredWood * numberToCraft;
		
		ItemStack finalCraft = new ItemStack(Material.CHEST, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.WOOD, (int) totalWood));
		player.sendMessage(ChatColor.GREEN + "Successfully crafted " + (int) numberToCraft + " chests, using " + totalWood + " wooden planks.");
	}
	
	public static void craftBucket(Player player) {
		int requiredIron = 3;
		int actualIron = getNumberOfMaterialInInventory(player, Material.IRON_INGOT);
		if (requiredIron < actualIron) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft a bucket. You need at least 3 iron ingots.");
			return;
		}
		
		double numberToCraft = Math.floor(actualIron / requiredIron);
		double totalIron = requiredIron * numberToCraft;
		
		ItemStack finalCraft = new ItemStack(Material.BUCKET, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, (int) totalIron));
		player.sendMessage(ChatColor.GREEN + "Successfully crafted " + (int) numberToCraft + " buckets, using " + totalIron + " iron ingots.");
	}
	
	public static void craftHopper(Player player) {
		int requiredChest = 1;
		int requiredIron = 5;
		
		int actualChest = getNumberOfMaterialInInventory(player, Material.CHEST);
		int actualIron = getNumberOfMaterialInInventory(player, Material.IRON_INGOT);
		
		if (actualChest < requiredChest || actualIron < requiredIron) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft TNT. You need at least 1 Chest and 5 Iron Ingots.");
			return;
		}
		
		double numberToCraft = 0;
		if ((actualChest / requiredChest) < (actualIron / requiredIron)) {
			numberToCraft = Math.floor(actualChest / requiredChest);
		} else {
			numberToCraft = Math.floor(actualIron / requiredIron);
		}
		
		int totalChest = (int) (requiredChest * numberToCraft);
		int totalIron = (int) (requiredIron * numberToCraft);
		
		ItemStack finalCraft = new ItemStack(Material.HOPPER, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, totalIron));
		player.getInventory().removeItem(new ItemStack(Material.CHEST, totalChest));
		player.sendMessage(ChatColor.GREEN + "Successfully crafted " + (int) numberToCraft + " Hopper(s), using " + totalChest + " chests and " + totalIron + " Iron Ingots.");
	}
	
	public static void craftTNT(Player player) {
		int requiredSand = 4;
		int requiredGunpowder = 5;
		
		int actualSand = getNumberOfMaterialInInventory(player, Material.SAND);
		int actualGunpowder = getNumberOfMaterialInInventory(player, Material.SULPHUR);
		
		if (actualSand < requiredSand || actualGunpowder < requiredGunpowder) {
			player.sendMessage(ChatColor.RED + "You do not have enough materials to craft TNT. You need at least " + requiredSand + " sand blocks and " + requiredGunpowder + " gunpowder.");
			return;
		}
		
		double numberToCraft = 0;
		if ((actualSand / requiredSand) < (actualGunpowder / requiredGunpowder)) {
			numberToCraft = Math.floor(actualSand / requiredSand);
		} else {
			numberToCraft = Math.floor(actualGunpowder / requiredGunpowder);
		}
		
		int totalSand = (int) (requiredSand * numberToCraft);
		int totalGunpowder = (int) (requiredGunpowder * numberToCraft);
		
		ItemStack finalCraft = new ItemStack(Material.TNT, (int) numberToCraft);
		player.getInventory().addItem(finalCraft);
		player.getInventory().removeItem(new ItemStack(Material.SAND, totalSand));
		player.getInventory().removeItem(new ItemStack(Material.SULPHUR, totalGunpowder));
		player.sendMessage(ChatColor.GREEN + "Successfully crafted " + (int) numberToCraft + " TNT, using " + totalSand + " sand and " + totalGunpowder + " gunpowder.");
		return;
		
	}
	
	public static int getNumberOfMaterialInInventory(Player player, Material mat) {
		ItemStack[] inv = player.getInventory().getContents();
		int quantity = 0;
		for(int i = 0; i < inv.length; i++) {
	        if(inv[i] != null){
	            if( inv[i].getType() == mat){
	                int cant = inv[i].getAmount();
	                quantity = quantity + cant;
	            }
	        }
	    }
		return quantity;
	}

}
