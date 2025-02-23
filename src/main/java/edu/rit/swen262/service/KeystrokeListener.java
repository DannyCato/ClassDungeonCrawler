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
}
