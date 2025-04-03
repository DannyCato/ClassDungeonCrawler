package edu.rit.swen262.service;

import java.util.HashMap;
import java.util.List;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.ItemComponent;
import edu.rit.swen262.service.Action.Action;
import edu.rit.swen262.service.Action.DisplayMenuAction;
import edu.rit.swen262.service.Action.DisplayMenuType;
import edu.rit.swen262.service.Action.UseItemAction;

public class ActionVisitor implements InventoryVisitor {
    private GameState gameState;
    private HashMap<Character, Action> actionKeystrokes;

    public ActionVisitor(GameState gameState) {
        this.gameState = gameState;
        this.actionKeystrokes = new HashMap<Character, Action>();
    }

    public void visitInventory(Inventory inventory) {
        List<Bag> contents = inventory.getBags();

        for (int i = 0; i < contents.size() ; i++) {
            List<ItemComponent> bagItems = List.copyOf(contents.get(i).getItems());
            String menuString = this.buildMenuString(bagItems);
            Action displayMenu = new DisplayMenuAction(gameState, DisplayMenuType.BAG, menuString);

            actionKeystrokes.put(Character.forDigit(i + 1, 10), displayMenu);
        }
    }
    
    public void visitBag(Bag bag) {
        List<Item> contents = bag.getItems();

        for (int i = 0; i < contents.size() ; i++) {
            String menuString = """
                    [1] Use Item
                    """;
            Action displayMenu = new DisplayMenuAction(gameState, DisplayMenuType.ITEM, menuString);

            actionKeystrokes.put(Character.forDigit(i + 1, 10), displayMenu);
        }
    }
    
    public void visitItem(Item item) {
        // implement functionality for dropping items here under key '2'
        this.actionKeystrokes = new HashMap<Character, Action>() {{
            put('1', new UseItemAction(gameState, item));
        }};
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
            displayText.append(String.format("[%s] %s\n", i + 1, items.get(i).getName()));
        }
		
		return displayText.toString();
	}

    public DisplayMenuAction getInventoryDisplayAction(Inventory inventory) {
        List<ItemComponent> contents = List.copyOf(inventory.getBags());

        String menuString = this.buildMenuString(contents);
        return new DisplayMenuAction(gameState, DisplayMenuType.INVENTORY, menuString);
    }

    public HashMap<Character, Action> getKeystrokes() {
        return this.actionKeystrokes;
    }
}
