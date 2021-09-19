package me.scill.creeperthrower;

import me.scill.creeperthrower.commands.ReloadCommand;
import me.scill.creeperthrower.data.MainConfig;
import me.scill.creeperthrower.listeners.ThrowListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class CreeperThrower extends JavaPlugin {

	private MainConfig config;
	private ImpactTimer impactTimer;

	public void onEnable() {
		config = new MainConfig(this);

		impactTimer = new ImpactTimer(this);
		impactTimer.runTaskTimer(this, 5L, 2L);

		new ThrowListener(this);
		new ReloadCommand(this);
	}

	public void onDisable() {
		HandlerList.unregisterAll();

		impactTimer.cancel();
		impactTimer.stop();

		config = null;
		impactTimer = null;
	}

	public MainConfig getMainConfig() {
		return config;
	}

	public ImpactTimer getImpactTimer() {
		return impactTimer;
	}
}
