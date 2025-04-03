package edu.rit.swen262.domain;

import edu.rit.swen262.service.InventoryVisitor;

/**
 * Defines a common interface for all item types in the game.
 * Ensures that integral behavior is consistent across classes.
 * 
 * @author [Nick F, Ryan M]
 */

public interface ItemComponent {

    /**
     * Gets the name of the item.
     *
     * @return The item's name.
     */
    String getName();
    
    /**
     * Gets the description of the item.
     *
     * @return A brief description of the item.
     */
    String getDescription();

    /**
     * Gets the value of the item in gold.
     *
     * @return The {@code Gold} value of the item.
     */
    Gold getValue();

    public void accept(InventoryVisitor visitor);
}