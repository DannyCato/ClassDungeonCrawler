package edu.rit.swen262.service;

import java.util.HashMap;
import java.util.Map;

import edu.rit.swen262.service.Action.Action;

/**
 * A class which parses input from the user, mapping the given String
 * to its corrosponding {@link Action}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class InputParser {
    private MenuState currentMenu;
    private HashMap<MenuState, HashMap<Character, Action>> keystrokes;

    /**
     * creates a new InputParser with the provided nested map of menu types to
     * another map linking single characters to specific 
     * {@link Action concrete commands} 
     * 
     * @param keystrokes map between characters and their matching commands
     */
    public InputParser(HashMap<MenuState, HashMap<Character, Action>> keystrokes) {
        this.keystrokes = keystrokes;
        this.currentMenu = MenuState.DEFAULT;
    }

    /**
     * given an input string from the {@link MUDGameUI UI class}, parses the
     * input and generates the appropriate {@link Action command}, then 
     * executes it
     * 
     * @param text the text inputted by the user
     */
    public void receivedInput(String text) {
        if (text.isBlank()) {
            return;
        }

        char input = text.toLowerCase().charAt(0);
        Action action = keystrokes.get(currentMenu).get(input);
        
        // once action has been fully constructed, execute the command
        if (action != null) {
            action.performAction();
        }
    }  
    /**
     * transitions the state of the input parser to the 
     * specified {@link MenuState menu}
     * 
     * @param menu the menu that is selected to be set
     */
    public void setMenu(MenuState menu) {
        this.currentMenu = menu;
    }
}
