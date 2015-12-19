package com.mistphizzle.easycraft;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands {

	EasyCraft plugin;

	public Commands(EasyCraft instance) {
		this.plugin = instance;
		init();
	}

	private void init() {
		PluginCommand tnt = plugin.getCommand("tnt"); 
		PluginCommand hopper = plugin.getCommand("hopper");
		PluginCommand chest = plugin.getCommand("chest"); 
		PluginCommand trappedchest = plugin.getCommand("trappedchest"); 
		PluginCommand easycraft = plugin.getCommand("easycraft"); //TODO
		PluginCommand easyfill = plugin.getCommand("easyfill"); 
		PluginCommand bucket = plugin.getCommand("bucket"); 
		PluginCommand goldingot = plugin.getCommand("goldingot");
		CommandExecutor exe;

		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				
				if (!s.hasPermission("easycraft.easycraft")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				player.openInventory(EasyCraft.easyInv);
				player.sendMessage(ChatColor.GREEN + "Opening EasyCraft GUI.");
				return true;
			}
		}; easycraft.setExecutor(exe);
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.goldingot")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftGoldIngot(player);
				return true;
			}
		}; goldingot.setExecutor(exe);
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.bucket")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftBucket(player);
				return true;
			}
		}; bucket.setExecutor(exe);
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.trappedchest")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftTrappedChest(player);
				return true;
			}
		}; trappedchest.setExecutor(exe);
		
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.chest")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftChest(player);
				return true;
			}
		}; chest.setExecutor(exe);
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.hopper")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftHopper(player);
				return true;
			}
		}; hopper.setExecutor(exe);
		
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.tnt")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				
				Player player = (Player) s;
				Methods.craftTNT(player);
				return true;
			}
		}; tnt.setExecutor(exe);
		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				double range = plugin.getConfig().getDouble("EasyFill.MaxRange");
				if (!(s instanceof Player)) {
					s.sendMessage(ChatColor.RED + "This command is only available to players.");
					return true;
				}
				if (!s.hasPermission("easycraft.easyfill")) {
					s.sendMessage(ChatColor.RED + "You don't have permission to do that.");
					return true;
				}
				if (args.length > 1) {
					s.sendMessage(ChatColor.RED + "Proper Usage: /easyfill [Range]");
					return true;
				}
				if (args.length == 1) {
					int range2 = Integer.parseInt(args[0]);
					if (range2 > range) {
						s.sendMessage(ChatColor.RED + "Adjusted range to the maximum range of " + range + ".");
					} else {
						range = range2;
					}
				}
				
				Player player = (Player) s;
								
				int tntQuantity = Methods.getNumberOfMaterialInInventory(player, Material.TNT);
			    
			    Set<Block> dispensersAround = new HashSet<Block>();
			    for (Block block: Methods.getBlocksAroundPoint(player.getLocation(), range)) {
			    	if (block.getType() == Material.DISPENSER) {
			    		dispensersAround.add(block);
			    	}
			    }
			    
			    if (dispensersAround.size() == 0) {
			    	s.sendMessage(ChatColor.RED + "There are no dispensers around within a range of " + range + " blocks.");
			    	return true;
			    }
			    
			    double perDispenser = tntQuantity / dispensersAround.size();
			    double finalPerDispenser = Math.floor(perDispenser);
			    
			    if (finalPerDispenser == 0) {
			    	s.sendMessage(ChatColor.RED + "You do not have enough TNT to spread evenly across " + dispensersAround.size() + " dispensers.");
			    	return true;
			    }
			    ItemStack stack = new ItemStack(Material.TNT, (int) finalPerDispenser);
			    
			    for (Block block: dispensersAround) {
			    	if (block.getState() instanceof Dispenser) {
			    		Dispenser dispenser = (Dispenser) block.getState();
				    	dispenser.getInventory().addItem(stack);
			    	}
			    	
			    }
			    int totalTNT = (int) finalPerDispenser * dispensersAround.size();
			    ItemStack stack2 = new ItemStack(Material.TNT, totalTNT);
			    player.getInventory().removeItem(stack2);
				s.sendMessage(ChatColor.GREEN + "Autofilled " + dispensersAround.size() + " dispensers with " + finalPerDispenser + " TNT in each.");
			    return true;
			}
		}; easyfill.setExecutor(exe);
	}
}
