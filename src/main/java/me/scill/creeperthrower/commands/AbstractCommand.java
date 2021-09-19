package me.scill.creeperthrower.commands;

import me.scill.creeperthrower.utils.CommonUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand implements CommandExecutor {

	private final String permission;

	public AbstractCommand(String permission) {
		this.permission = permission;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!sender.hasPermission(permission))
			sender.sendMessage(CommonUtil.color("&cYou lack the permissions for this command."));

		else if (args.length == 0 || !onCommand(sender, args))
			sender.sendMessage(getCommandHelpMessage());

		return true;
	}

	protected abstract boolean onCommand(CommandSender sender, String[] args);

	protected abstract String getCommandHelpMessage();
}