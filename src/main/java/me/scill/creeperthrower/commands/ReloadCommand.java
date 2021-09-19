package me.scill.creeperthrower.commands;

import me.scill.creeperthrower.CreeperThrower;
import me.scill.creeperthrower.utils.CommonUtil;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends AbstractCommand {

	private final CreeperThrower plugin;

	public ReloadCommand(CreeperThrower plugin) {
		super("creeperthrower.reload");

		this.plugin = plugin;
		plugin.getCommand("creeperthrower").setExecutor(this);
	}

	@Override
	protected boolean onCommand(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			plugin.getMainConfig().reload();
			return true;
		}

		return false;
	}

	@Override
	protected String getCommandHelpMessage() {
		return CommonUtil.color("&c/creeperthrower reload");
	}
}
