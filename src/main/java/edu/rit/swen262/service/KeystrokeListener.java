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
        
        switch (input) {
            case 'm':
                //reference Menu
                //moveAction will generate menu
                break;
            case 'a':
                //attackAction will generate menu
                break;
            case 'i':
                //inventoryAction will generate menu
                break;
            case 'c':
                //chestAction
                break;
            case 'd':
                //disarmTrapAction will generate menu
                break;
            case 'q':
                //quit
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
    }  
}
