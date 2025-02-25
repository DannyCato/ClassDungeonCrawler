package edu.rit.swen262.service;

import java.util.Map;

/**
 * A class which parses input from the user, mapping the given String
 * to its corrosponding {@link Action}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class InputParser {
    private Menu currentMenu;
    private Map<Character, Action> keystrokes;

    /**
     * creates a new InputParser with the provided map of single
     * characters bound to specific {@link Action concrete commands} 
     * 
     * @param keystrokes map between characters and their matching commands
     */
    public InputParser(Map<Character, Action> keystrokes, Menu menu) {
        this.keystrokes = keystrokes;
        this.currentMenu = menu;
    }

    /**
     * given an input string from the {@link MUDGame UI class}, parses the
     * input and generates the appropriate command, then executes it
     * 
     * @param text the text inputted by the user
     */
    public void receivedInput(String text) {
        if (text.isBlank()) {
            return;
        }

        char input = text.toLowerCase().charAt(0);
        Action action = keystrokes.get(input);
        
        // once action has been fully constructed, execute the command
        if (action != null) {
            action.performAction();
        }
    }  
}
