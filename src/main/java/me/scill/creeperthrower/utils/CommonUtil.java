package me.scill.creeperthrower.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommonUtil {

	private final static Random random = new Random();

	/**
	 * If the message contains any valid
	 * color codes, they will be applied.
	 *
	 * @param message any string
	 * @return colored version of the string
	 */
	public static String color(final String message) {
		if (message == null)
			return "";
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	/**
	 * If the list of messages contains any
	 * valid color codes, they will be applied.
	 *
	 * @param messages any list of strings
	 * @return colored version of the list
	 */
	public static List<String> color(final List<String> messages) {
		final List<String> newList = new ArrayList<>();
		if (messages == null || messages.isEmpty())
			return newList;

		for (String message : messages)
			newList.add(color(message));
		return newList;
	}

	/**
	 * If the list of messages contains any
	 * valid color codes, they will be applied.
	 *
	 * @param messages any list of strings
	 * @return colored version of the list
	 */
	public static List<String> color(final String...messages) {
		return color(Arrays.asList(messages));
	}
}