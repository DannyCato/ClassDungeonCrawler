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
    private String defaultMenuString;
    private Action menuAction;
    private HashMap<DisplayMenuType, HashMap<Character, Action>> keystrokeMap;

    public InteractionResult(Character defaultKeystroke, Action menuAction, HashMap<DisplayMenuType, HashMap<Character, Action>> keystrokeMap) {
        this.defaultKeystroke = defaultKeystroke;
        this.menuAction = menuAction;
        this.keystrokeMap = keystrokeMap;

        this.defaultMenuString = String.format("[%s] %s", defaultKeystroke, this.menuAction);
    }

    public Action getAction() {
        return this.menuAction;
    }

    public HashMap<DisplayMenuType, HashMap<Character, Action>> getKeystrokes() {
        return this.keystrokeMap;
    }

    public Character getDefaultKeystroke() {
        return this.defaultKeystroke;
    }

    public String getDefaultMenuString() {
        return this.defaultMenuString;
    }
}
