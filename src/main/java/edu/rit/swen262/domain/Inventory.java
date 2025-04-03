package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.IObservable;
import edu.rit.swen262.service.InventoryVisitor;
import edu.rit.swen262.service.GameEvent;
import edu.rit.swen262.service.GameEventType;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.IObservable;

/**
 * Represents a player's inventory, which can hold multiple bags.
 * Bags are used to store extra items.
 * 
 * @author [Nick F, Ryan M]
 */

public class Inventory implements ItemComponent {

    /** The list of bags the the player currently has. */
    private List<Bag> bags;
    /** The amount of bags a player can carry. */
    private int capacity;

    /**
     * Creates an inventory with a specific capacity.
     * Creates an inventory with empty bags, default set at 6.
     * 
     * @param capacity The maximum number of bags a player can have.
     */
    public Inventory(int capacity) {
        this.bags = new ArrayList<Bag>();
        for (int i = 0; i < capacity; i++) {
            bags.add(new Bag(6));
        }
        this.capacity = capacity;
    }

    /**
     * Creates an inventory with a list of bags and quantity.
     * 
     * @param bags The list of bags stored in the inventory.
     * @param capacity The capacity of bags a player can hold.
     */
    public Inventory(List<Bag> bags, int capacity) {
        this.bags = bags;
        this.capacity = capacity;
    }

    /**
     * Tries to add a bag if there is space.
     * @param bag The bag to be added.
     * @return Returns {@code true} if the bag was added, {@code false} otherwise.
     */
    public boolean addBag(Bag bag) {
        if (bags.size() < capacity) {
            return bags.add(bag);
        }
        return false;

    }

    public boolean addItem(Item item) {
        for (Bag bag : this.bags) {
            if (!bag.isFull()) {
                bag.addItem(item);
                return true;
            }
        }

        return false;
    }

    /**
     * Tries to remove a bag from the inventory.
     * @param bag The bag to be removed.
     * @return Returns {@code true} if the bag was removed, {@code false} otherwise.
     */
    public boolean removeBag(Bag bag) {
        if (bags.size() > 0) {
            return bags.remove(bag);
        }
        return false;
    }

    /**
     * Gets a list of all of the bags a player has.
     * @return the bags a player has.
     */
    public List<Bag> getBags() {
        return bags;

    }

    /**
     * Gets the total amount of bags a player has.
     * @return the total amount of bags a player has.
     */
    public int getTotalBags() {
        return bags.size();

    }

    /**
     * Gets the maximum capacity of bags a player can hold.
     * @return the maximum capacity of bags a player can hold.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
    * Gets the names of the inventory
    * @return The name of the inventory as a string
    */
    public String getName(){
        return "Big uhhh Big Inventory Guy";
    }

    /**
    * Gets the description of the inventory
    * @return The description of the inventory
    */
    public String getDescription(){
        return "I'm filled of little goodies :3";
    }

    /**
    * Gets the goldValue of the inventory
    * @return The {@link Gold Gold} Value of the inventory
    */
    public Gold getValue() {
        return new Gold(0);
    }
    
    /**
     * Accepts a visitor to allow the vistor to perform operations on this inventory
     * 
     * @param visitor the Visitor that will perform operations on this inventory
     */
    public void accept(InventoryVisitor visitor) {
        visitor.visitInventory(this);
    }
}
