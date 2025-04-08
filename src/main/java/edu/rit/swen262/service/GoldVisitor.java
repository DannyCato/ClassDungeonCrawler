package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Gold;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.ItemComponent;

/**
 * The class is a specific implementation of the Visitor Pattern
 * Defines the concrete operations to be performed on elements within the inventory system
 * that affect the current gold value of the current {@link Player Player's} {@link Inventory inventory}
 * 
 * The GoldVisitor traverses the inventory, bag, and item elements and stores their goldValues
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class GoldVisitor implements InventoryVisitor {
    private Gold goldValue;

     /**
     * Visits an Inventory element and gets the total Gold value
     * of the element and appends it to goldValue
     * 
     * @param inventory The Inventory element that is operated on
     */
    public void visitInventory(Inventory inventory) {
        this.goldValue = inventory.getValue();
    }

    /**
     * Visits a Bag element and gets the total Gold value
     * of the element and appends it to goldValue
     * 
     * @param bag The Bag element that is operated on
     */
    public void visitBag(Bag bag) {
        this.goldValue = bag.getValue();
    }

    /**
     * Visits an Item element and gets the total Gold value
     * of the element and appends it to goldValue
     * 
     * @param item The Item element that is operated on
     */
    public void visitItem(Item item) {
        this.goldValue = item.getValue();
    }

    /**
     * Retrieves the goldValue
     * 
     * @return current goldValue
     */
    public Gold getGold() {
        return this.goldValue;
    }
}
