package me.scill.creeperthrower;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpactTimer extends BukkitRunnable implements Listener {

	private List<ThrowableCreeper> creepers = new ArrayList<>();

	public ImpactTimer(CreeperThrower plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public List<ThrowableCreeper> getCreepers() {
		return creepers;
	}

	public void stop() {
		creepers.forEach(ThrowableCreeper::kill);
		creepers = null;
	}

	@Override
	public void run() {
		if (creepers.isEmpty())
			return;

		Iterator<ThrowableCreeper> iterator = creepers.iterator();

		while (iterator.hasNext()) {
			ThrowableCreeper creeper = iterator.next();
			if (creeper.isDead())
				iterator.remove();
			else if (creeper.isOld() || creeper.checkForImpact()) {
				creeper.explode();
				iterator.remove();
			}
		}
	}

	@EventHandler
	public void onCreeperDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Creeper) || creepers.isEmpty())
			return;

		for (ThrowableCreeper creeper : creepers) {
			if (creeper.isCreeperEqual((Creeper) event.getEntity())) {
				event.setCancelled(true);
				return;
			}
		}
	}
}
