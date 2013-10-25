package com.martinbrook.FlyingBoots;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


public class FlyingBootsListener implements Listener {

	private FlyingBoots plugin;
	
	public FlyingBootsListener(FlyingBoots plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (!plugin.isActive()) return;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				plugin.bootsUpdate((Player)e.getWhoClicked());
			}
		});
	}
	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if (!plugin.isActive()) return;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				plugin.bootsUpdate(e.getPlayer());
			}
		});
	}
	
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent e) {
		if (!plugin.isActive()) return;
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				plugin.bootsCheck();
			}
		});
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		plugin.bootsUpdate(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		plugin.bootsUpdate(e.getPlayer());
	}
}
