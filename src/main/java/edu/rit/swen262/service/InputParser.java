package edu.rit.swen262.service;

import java.util.HashMap;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.service.Action.Action;
import edu.rit.swen262.service.Action.DisplayMenuAction;
import edu.rit.swen262.service.Action.UseItemAction;

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
    private char lastPressedKey;

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
                this.visitor.visitInventory(this.inventory);
                keystrokes.put(MenuState.INVENTORY, visitor.getKeystrokes());
                break;
            case INVENTORY:
                this.visitor.visitBag(this.inventory.getBags().get(((int) input) - 1));
                keystrokes.put(MenuState.BAG, visitor.getKeystrokes());
            case BAG:
                Bag bag = this.inventory.getBags().get(((int) lastPressedKey) - 1);
                this.visitor.visitItem(bag.getItems().get(((int) input) - 1));
                keystrokes.put(MenuState.ITEM, visitor.getKeystrokes());
                break;
            default:
                // not inventory-related, do nothing
                break;
        }

        Action action = keystrokes.get(currentMenu).get(input);

        
        // once action has been fully constructed, execute the command
        if (action != null) {
            action.performAction();
        }
        this.currentMenu.handleInput(this, input);
        this.lastPressedKey = input;
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
