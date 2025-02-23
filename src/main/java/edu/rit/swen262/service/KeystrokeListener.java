package edu.rit.swen262.service;

import java.util.Map;

/**
 * 
 */
public class KeystrokeListener {
    private Map<String, Action> keystrokes;

    public KeystrokeListener(Map<String, Action> keystrokes) {
        this.keystrokes = keystrokes;
    }

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
        action.performAction();
    }  
}
