package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.ItemComponent;
import edu.rit.swen262.service.Action.Action;
import edu.rit.swen262.service.Action.DisplayMenuAction;
import edu.rit.swen262.service.Action.DisplayMenuType;
import edu.rit.swen262.service.Action.DropItemAction;
import edu.rit.swen262.service.Action.UseItemAction;


/**
 * The class is a specific implementation of the Visitor interface
 * Defines the concrete operations to be performed on elements within the inventory system
 * that affect the current {@link Item Item's} of the current {@link Player Player's} {@link Inventory inventory}
 * 
 * The ActionVisitor traverses the inventory, bag, and item elements and generates the
 * corrosponding hashmaps of {@link Action Actions} bound to a numeric keystroke and 
 * meant be executed by the game
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class ActionVisitor implements InventoryVisitor {
    private GameState gameState;
    private HashMap<Character, Action> actionKeystrokes;

    /**
     * Constructs an Action Visitor with a given GameState
     * Initializes the actionkeystrokes HashMap which stores the keystrokes
     * which are associated with different {@link Action Actions}
     * 
     * @param gameState The current state of the game
     */
    public ActionVisitor(GameState gameState) {
        this.gameState = gameState;
        this.actionKeystrokes = new HashMap<Character, Action>();
    }

    /**
     * Visits an Inventory element and generates a list of action keystrokes
     * for interacting with each bag within the inventory. Each keystroke
     * will then trigger the display of a {@link DisplayMenuAction menu} 
     * for the contents of a specific bag
     * 
     * @param inventory The Inventory element that is operated on
     */
    public void visitInventory(Inventory inventory) {
        this.actionKeystrokes = new HashMap<Character, Action>();
        List<Bag> contents = inventory.getBags();

        for (int i = 0; i < contents.size() ; i++) {
            List<ItemComponent> bagItems = List.copyOf(contents.get(i).getItems());
            String menuString = this.buildMenuString(bagItems);
            Action displayMenu = new DisplayMenuAction(gameState, DisplayMenuType.BAG, menuString);

            actionKeystrokes.put(Character.forDigit(i + 1, 10), displayMenu);
        }
    }
    
    /**
     * Visits an Bag element and generates a list of action keystrokes
     * for interacting with each item within the bag. Each keystroke
     * will then trigger the display of a {@link DisplayMenuAction menu} 
     * for the actions that can be performed with a specific item
     * 
     * @param bag The Bag element that is operated on
     */
    public void visitBag(Bag bag) {
        this.actionKeystrokes = new HashMap<Character, Action>();
        List<Item> contents = bag.getItems();

        for (int i = 0; i < contents.size() ; i++) {
            String menuString = """
                    [1] Use Item
                    [2] Drop Item
                    """;
            Action displayMenu = new DisplayMenuAction(gameState, DisplayMenuType.ITEM, menuString);

            actionKeystrokes.put(Character.forDigit(i + 1, 10), displayMenu);
        }
    }
    
    /**
     * Visits an Item element and generates a list of {@link Action Actions}
     * relating to the use of an item
     * 
     * @param item The Item element that is operated on
     */
    public void visitItem(Item item) {
        this.actionKeystrokes = new HashMap<Character, Action>();

        this.actionKeystrokes.put('1', new UseItemAction(gameState, item));
        this.actionKeystrokes.put('2', new DropItemAction(gameState, item));
    }

    /**
	 * helper method which produces a string representation of a list of
     * {@link ItemComponent}, producing a list in the format of:
	 * '[n] <Action>' for each item
	 * 
	 * @param items a list of {@link ItemComponents}
	 * @return a String representation of the given list
	 */
	private String buildMenuString(List<ItemComponent> items) {
        StringBuilder displayText = new StringBuilder();
        
        for (int i = 0; i < items.size(); i++) {
            ItemComponent iComponent = items.get(i);
            displayText.append(String.format("[%s] %s", i + 1, iComponent.getName()));
            if (iComponent instanceof Item) {
                displayText.append(String.format(" (%s)", iComponent.getClass().getSimpleName()));
            }
            displayText.append("\n");
        }
		
		return displayText.toString();
	}

    /**
     * Returns a DisplayMenuAction that represents the Action of displaying the 
     * contents of the {@link Inventory}. This is used for diplaying the overall 
     * inventory to the user at the top level, including all bags and their contents.
     * 
     * @param inventory The Inventory element to generate a display action for.
     * @return A {@link DisplayMenuAction} that will display the inventory's contents.
     */
    public DisplayMenuAction getInventoryDisplayAction(Inventory inventory) {
        List<ItemComponent> contents = List.copyOf(inventory.getBags());

        String menuString = this.buildMenuString(contents);
        return new DisplayMenuAction(gameState, DisplayMenuType.INVENTORY, menuString);
    }

    /**
     * Retrieves the action keystrokes associated with the current GameState
     * 
     * @return A Hashmap of keys which are Characters and values which are the associated Actions
     */
    public HashMap<Character, Action> getKeystrokes() {
        return this.actionKeystrokes;
    }
}
