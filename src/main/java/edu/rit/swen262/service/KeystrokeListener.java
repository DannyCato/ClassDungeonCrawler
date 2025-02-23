package edu.rit.swen262.service;

import java.util.Map;

import com.googlecode.lanterna.gui2.TextBox.TextChangeListener;

/**
 * 
 */
public class KeystrokeListener implements TextChangeListener {
    private Map<String, Action> keystrokes;

    public KeystrokeListener(Map<String, Action> keystrokes) {
        this.keystrokes = keystrokes;
    }

    /**
     * {@inheritDoc}
     */
    public void onTextChanged(String newText, boolean changedByUserInteraction) {
        if (!changedByUserInteraction) {
            return;
        }

        // may not be neccessary/break things? further testing needed w/ how TextBox takes in input
        if (newText.endsWith("\n")) {
            String commandText = newText.trim();

            if (commandText != null) {
                if (commandText.equals("q")) {
                }
            } else {

            }
        }
    }
}
