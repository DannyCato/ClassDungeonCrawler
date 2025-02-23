package edu.rit.swen262.service;

import java.util.Map;

/**
 * A class which parses input from the user, mapping the given String
 * to its corrosponding {@link Action}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class KeystrokeListener {
    private Map<String, Action> keystrokes;

    /**
     * creates a new KeystrokeListener with the provided map of single
     * characters bound to specific {@link Action concrete commands} 
     * 
     * @param keystrokes map between characters and their matching commands
     */
    public KeystrokeListener(Map<String, Action> keystrokes) {
        this.keystrokes = keystrokes;
    }

    /**
     * given an input string from the {@link MUDGame UI class}, parses the
     * input and generates the appropriate command, then executes it
     * 
     * @param text the text inputted by the user
     */
    public void receivedInput(String text) {
        char input = text.toLowerCase().charAt(0);
        Action action = null;
        
        switch (input) {
            case 'm':
                //reference Menu
                //moveAction will generate menu
                System.out.println("I AM MOVING!");
                break;
            case 'a':
                //attackAction will generate menu
                System.out.println("I AM ATTACKING!");
                break;
            case 'i':
                //inventoryAction will generate menu
                System.out.println("I AM OPENING MY INVENTORY");
                break;
            case 'c':
                //chestAction
                System.out.println("I AM OPENING THIS CHEST");
                break;
            case 'd':
                //disarmTrapAction will generate menu
                System.out.println("I AM DISARMING THIS TRAP");
                break;
            case 'q':
                //quit
                System.out.println("I QUIT!");
                action = keystrokes.get("q");
                break;
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
            case '6':
                break;
            case '7':
                break;
            case '8':
                break;
            default:
                break;
        }

        // once action has been fully constructed, execute the command
        action.performAction();
    }  
}
