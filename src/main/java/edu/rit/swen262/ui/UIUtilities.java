package edu.rit.swen262.ui;

import java.util.HashMap;

import edu.rit.swen262.service.Action.Action;

public class UIUtilities {
    /**
	 * helper method which produces a string representation of a hash map of
	 * {@link Action commands}, producing a list in the format of:
	 * '[n] <Action>' for every command and its associated number value
	 * 
	 * @param commandMap map which associates a set of {@link Action commands}
	 * with a different keystroke
	 * @return a String representation of the given command hashmap
	 */
	public static String buildMenuString(HashMap<Character, Action> commandMap) {
        StringBuilder displayText = new StringBuilder();
        commandMap.forEach((key, value) -> 
			displayText.append(String.format("[%s] %s\n", key, value))
		);
		
		return displayText.toString();
	}
}
