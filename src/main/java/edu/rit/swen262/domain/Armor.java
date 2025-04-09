package edu.rit.swen262.domain;

/**
 * Represents an Armor item in game, increasing the player's defensive stats.
 * Extends from {@code Item}
 * 
 * @author [Nick F, Ryan M]
 */

public class Armor extends Item {

    /** Defense value for the armor item */
    private int defense;

    /**
     * 
     * Creates an Armor item using the specified value
     * 
     * @param name The name of the armor
     * @param description A description of the armor
     * @param defense A defense value associated with the armor
     * @param value The value of the armor given in gold
     */
    public Armor(String name, String description, int defense, Gold value) {
        super(name, description, value);
        this.defense = defense;
    }
    
    /**
     * @return Gets the defense value of the armor
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns a clean string representation of an armor item, using the name, description, defense, and count.
     * @return A string describing the Armor.
     */
    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nDefense: %s%nValue: %s", getName(), getDescription(), getDefense(), gold.getCount());
    }
}
