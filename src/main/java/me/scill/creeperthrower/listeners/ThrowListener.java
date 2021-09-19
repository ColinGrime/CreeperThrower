package me.scill.creeperthrower.listeners;

import me.scill.creeperthrower.CreeperThrower;
import me.scill.creeperthrower.ThrowableCreeper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ThrowListener implements Listener {

	private final CreeperThrower plugin;

	public ThrowListener(CreeperThrower plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onCreeperEggThrow(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_AIR)
			return;

		Player player = event.getPlayer();
		ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

		if (itemInMainHand.getType() != Material.CREEPER_SPAWN_EGG)
			return;

		if (itemInMainHand.getAmount() > 1)
			itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
		else
			player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));

		spawnCreeper(player.getLocation());
	}

	private void spawnCreeper(Location location) {
		double throwSpeed = plugin.getMainConfig().getThrowSpeed();
		int explosionRadius = plugin.getMainConfig().getExplosionRadius();
		plugin.getImpactTimer().getCreepers().add(new ThrowableCreeper(location, throwSpeed, explosionRadius));
	}
}
