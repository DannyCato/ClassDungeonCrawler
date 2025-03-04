package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bag that can hold items, acts as a container
 * Has a defined capacity to store a specific amount of items
 * 
 * @author [Nick F, Ryan M]
 */

public class Bag {

    /** 
     * The items stored in a bag.
    */
    private List<Item> contents;
    /**
     * The number of items a bag can hold.
     */
    private int capacity;

    /**
     * Creates a bag with a specific capacity as an empty list.
     * 
     * @param capacity The maxmium number of items a bag can hold.
     */
    public Bag(int capacity) {
        this.contents = new ArrayList<Item>();
        this.capacity = capacity;
    }

    /**
     * Creates a bag a list of items and specific capacity.
     * 
     * @param contents The items that the bag holds.
     * @param capacity The maximum number of items a bag can hold.
     */
    public Bag(List<Item> contents, int capacity) {
        this.contents = contents;
        this.capacity = capacity;
    }

    /**
     * Tries to add an item to a bag if it is not full
     * 
     * @param item The item that will try to get added.
     * @return Returns {@code true} if the item was added, {@code false} if it was not.
     */
    public boolean addItem(Item item) {
        if (contents.size() < capacity) {
            return contents.add(item);
        }
        return false;

    }

    /**
     * Tries to remove an item from the bag.
     * 
     * @param item The item that will be removed.
     * @return Returns {@code true} if the item was removed, {@code false} if it was not.
     */
    public boolean removeItem(Item item) {
        return contents.remove(item);

    }

    /**
     * Gets the contents stored in a bag.
     * @return The list of item objects in a bag.
     */
    public List<Item> getItems() {
        return contents;

    }

    /**
     * Gets the maxmimum capacity of a bag.
     * @return The capacity of a bag.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Compares a bag to another bag for equality.
     * The two are considered equal if both contain the same items and have the same capacity.
     * 
     * @param obj The object that will be compared to.
     * @return Returns {@code true} if the objects are equal, {@code false} if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bag) {
            Bag other = (Bag) obj;
            return (contents.equals(other.contents)) && capacity == other.capacity;
        } else {
            return false;
        }
    }

}
