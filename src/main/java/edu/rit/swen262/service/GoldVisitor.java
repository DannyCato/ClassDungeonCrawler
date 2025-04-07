package edu.rit.swen262.service;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Gold;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;

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

    public void visitInventory(Inventory inventory) {
        
    }

    public void visitBag(Bag bag) {

    }

    public void visitItem(Item item) {
        
    }
}
