package me.scill.creeperthrower.data;

import me.scill.creeperthrower.CreeperThrower;

public class MainConfig {

	private final CreeperThrower plugin;

	private double throwSpeed;
	private int explosionRadius;

	public MainConfig(CreeperThrower plugin) {
		this.plugin = plugin;

		plugin.saveDefaultConfig();
		reload();
	}

	public void reload() {
		plugin.reloadConfig();
		throwSpeed = plugin.getConfig().getDouble("throw-speed");
		explosionRadius = plugin.getConfig().getInt("explosion-radius");
	}

	public double getThrowSpeed() {
		return throwSpeed;
	}

	public int getExplosionRadius() {
		return explosionRadius;
	}
}
