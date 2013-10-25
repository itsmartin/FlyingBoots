package com.martinbrook.FlyingBoots;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyingBoots extends JavaPlugin {
	private boolean enabled;

	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new FlyingBootsListener(this), this);
		this.enabled = false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("flyingboots.toggle") && command.getName().equalsIgnoreCase("flyingboots")) {
			enabled = !enabled;
			if (enabled) {
				sender.sendMessage(ChatColor.RED + "Flying boots enabled");
				getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						bootsCheck();
					}
				}, 20L, 20L);
			} else {
				sender.sendMessage(ChatColor.GREEN + "Flying boots disabled");
				getServer().getScheduler().cancelTasks(this);
			}
			bootsCheck();
		}
		
		return true;
	}
	
	public boolean isActive() {
		return enabled;
	}
	/**
	 * Check all players flying status and update it
	 */
	public void bootsCheck() {
		for (Player p : getServer().getOnlinePlayers()) bootsUpdate(p);
	}
	
	public void bootsUpdate(Player p) {
		if (p.getGameMode()==GameMode.CREATIVE) return;
		if (enabled && p.getInventory().getArmorContents()[0].getType() == Material.GOLD_BOOTS) {
			if (!p.getAllowFlight()) {
				p.setAllowFlight(true);
				// If player is falling, stop them falling
				if (!((Entity)p).isOnGround()) p.setFlying(true);
			}
		} else {
			p.setAllowFlight(false);
		}
	}
}
