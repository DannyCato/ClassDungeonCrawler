package edu.rit.swen262.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.service.Action.Action;
import edu.rit.swen262.service.Action.DisplayMenuAction;
import edu.rit.swen262.ui.MUDGameUI;

/**
 * A class which parses input from the user, mapping the given String
 * to its corrosponding {@link Action}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class InputParser {
    protected MenuState currentMenu;
    private HashMap<MenuState, HashMap<Character, Action>> keystrokes;
    private ActionVisitor visitor;
    private Inventory inventory;
    private final int HISTORY_SIZE = 3; // number of most recent keystrokes kept in memory
    private final Deque<Character> keystrokeHistory = new ArrayDeque<>(HISTORY_SIZE);

    /**
     * creates a new InputParser with the provided nested map of menu types to
     * another map linking single characters to specific 
     * {@link Action concrete commands} 
     * 
     * @param keystrokes map between characters and their matching commands
     */
    public InputParser(HashMap<MenuState, HashMap<Character, Action>> keystrokes, ActionVisitor visitor, Inventory inventory) {
        this.keystrokes = keystrokes;
        this.visitor = visitor;
        this.inventory = inventory;
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

        switch(currentMenu) {
            case DEFAULT:
                // update DisplayMenuAction inside of DefaultMenu keystroke map
                DisplayMenuAction inventoryDisplayMenu = visitor.getInventoryDisplayAction(inventory);
                keystrokes.get(currentMenu).put('i', inventoryDisplayMenu);
            case INVENTORY:
                // update list of bags inside inv which can be selected
                this.visitor.visitInventory(this.inventory);
                keystrokes.put(MenuState.INVENTORY, visitor.getKeystrokes());
                break;
            case BAG:
                // update list of items inside selected bag which can be selected
                this.visitor.visitBag(this.inventory.getBags().get(Character.getNumericValue(getLast()) - 1));
                keystrokes.put(MenuState.BAG, visitor.getKeystrokes());
                break;
            case ITEM:
                // update list of interactions available for the selected item
                Bag bag = this.inventory.getBags().get(Character.getNumericValue(getSecondLast()) - 1);
                this.visitor.visitItem(bag.getItems().get((Character.getNumericValue(getLast()) - 1)));
                
                keystrokes.put(MenuState.ITEM, visitor.getKeystrokes());
                break;
            default:
                break;
        }

        Action action = keystrokes.get(currentMenu).get(input);

        // once action has been fully constructed, execute the command
        if (action != null) {
            action.performAction();
        }
        this.currentMenu.handleInput(this, input);

        //remove oldest input from history and add new one at tail
        if (keystrokeHistory.size() == 3) {
            keystrokeHistory.pollFirst();
        }
        keystrokeHistory.addLast(input);
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

    private Character getLast() {
        return keystrokeHistory.peekLast();
    }

    private Character getSecondLast() {
        if (keystrokeHistory.size() < 2) return null;
        return keystrokeHistory.toArray(new Character[0])[keystrokeHistory.size() - 2]; // Second last keystroke
    }
}
