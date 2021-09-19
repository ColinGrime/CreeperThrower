package me.scill.creeperthrower;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.Objects;

public class ThrowableCreeper {

	private final long spawnedTimer = System.currentTimeMillis();
	private final Creeper creeper;
	private final int yPosition;

	private boolean isFallingStraightDown = false;
	private double oldSpeedY = 0;

	public ThrowableCreeper(Location location, double throwSpeed, int explosionRadius) {
		creeper = (Creeper) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.CREEPER);
		creeper.setVelocity(location.getDirection().add(new Vector(0.02, 0, 0.02)).multiply(throwSpeed));
		creeper.setExplosionRadius(explosionRadius);

		yPosition = location.getBlockY();
	}

	public boolean isCreeperEqual(Creeper creeper) {
		return this.creeper.equals(creeper);
	}

	public void kill() {
		creeper.setHealth(0);
	}

	public void explode() {
		if (!isDead())
			creeper.explode();
	}

	public boolean isDead() {
		return creeper == null || creeper.isDead();
	}

	public boolean isOld() {
		return System.currentTimeMillis() >= spawnedTimer + (1000 * 10L);
	}

	public boolean checkForImpact() {

		Vector creeperSpeed = creeper.getVelocity();

		// Hit its head on something
		if (!isFallingStraightDown && creeperSpeed.getY() <= 0 && oldSpeedY > 0.3)
			return true;

		// Falling down (not launched down)
		if (creeper.getLocation().getBlockY() >= yPosition + 10 && creeperSpeed.getY() <= 0 && oldSpeedY > 0)
			isFallingStraightDown = true;

		// Block is below them, not being pushed away too fast
		if ((creeper.getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR && creeperSpeed.length() < 0.5)
				// Hit something while not falling down straight down
				|| (!isFallingStraightDown && (creeperSpeed.getX() == 0 || creeperSpeed.getZ() == 0))
				// Well it's not moving!
				|| (creeperSpeed.getY() == 0 && oldSpeedY > 0))
		{
			return true;
		}

		oldSpeedY = creeperSpeed.getY();
		return false;
	}
}
