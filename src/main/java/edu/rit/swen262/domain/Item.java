package edu.rit.swen262.domain;

import edu.rit.swen262.service.InventoryVisitor;

import edu.rit.swen262.service.InventoryVisitor;

/**
 * Represents an item in the game.
 * Notably abstract, as it serves the purpose of being a base class for all item types.
 * Implements the {@code ItemComponent} interface.
 * 
 * @author [Nick F, Ryan M]
 */
public abstract class Item implements ItemComponent {
    
    /** The name of an item */
    private String name;
    /** The description of an item */
    private String description;
    /** The value of an item in Gold. */
    private Gold value;

    /**
     * Creates an item with the given parameters.
     * 
     * @param name The name of an item.
     * @param description The description of an item.
     * @param value The value of an item.
     */
    public Item(String name, String description, Gold value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    /**
     * Returns the name of an item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of an item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the value of an item.
     */
    public Gold getValue() {
        return value;
    }

    /**
     * Accepts a visitor to allow the vistor to perform operations on this Item
     * 
     * @param visitor the Visitor that will perform operations on this Item
     */
    public void accept(InventoryVisitor visitor) {
        visitor.visitItem(this);
    }

    /**
     * Compares an item to another item for equality.
     * If both items have the same description and name, they are considered equal.
     * 
     * @param obj The object that will be compared to.
     * @return {@code true} if the items are equal, {@code false} if they are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item other = (Item) obj;
            return (description.equals(other.description)) && name.equals(other.name);
        } else {
            return false;
        }
    }

    /**
     * Returns a clean string representation of an Item including the
     * name, description, and count.
     * 
     * @return A string describing an Item.
     */
    @Override
    public String toString() {
        return String.format("%s: %s%nValue: %s", name, description, value.getCount());
    }
}
