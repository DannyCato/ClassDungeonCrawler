package edu.rit.swen262.service;

import edu.rit.swen262.domain.Bag;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.domain.Item;

/**
 * The InventoryVisitor interface defines the contract for implementing Concrete Visitors
 * which interact with different components of the {@link Inventory Inventory} System.
 * This interface allows actions to be performed on by elements of the inventory, bags, and items
 * without directly modifying the elements themselves.
 * 
 * @author Victor Bovat, Philip
 */
public interface InventoryVisitor {
    public void visitInventory(Inventory inventory);
    public void visitBag(Bag bag);
    public void visitItem(Item item);
}
