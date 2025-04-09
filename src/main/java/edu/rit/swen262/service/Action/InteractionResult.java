package edu.rit.swen262.service.Action;

import java.util.HashMap;

/**
 * a container class for the {@link InteractionActionFactory} which pairs together
 * a single action to a map containing keystrokes paired to actions. Used to pass all necessary 
 * information to the {@link MUDGameUI} and {@link InputParser} when the player
 * should be notified of new interactable actions with {@link Occupants}
 * 
 * @author Victor Bovat
 */
public class InteractionResult {
    private Character defaultKeystroke;
    private Action menuAction;
    private HashMap<Character, Action> keystrokeMap;

    public InteractionResult(Character defaultKeystroke, Action menuAction, HashMap<Character, Action> keystrokeMap) {
        this.defaultKeystroke = defaultKeystroke;
        this.menuAction = menuAction;
        this.keystrokeMap = keystrokeMap;
    }

    public Action getAction() {
        return this.menuAction;
    }

    public HashMap<Character, Action> getKeystrokes() {
        return this.keystrokeMap;
    }

    public Character getDefaultKeystroke() {
        return this.defaultKeystroke;
    }
}
