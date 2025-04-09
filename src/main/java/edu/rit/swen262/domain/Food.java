package edu.rit.swen262.domain;

/**
 * Represents a Food item that restores the player's health when used.
 * Extends the {@code Item} interface.
 * 
 * @author [Nick F, Ryan M]
 */
public class Food extends Item {

    /** The amount of HP that will be restored when the item is used. */
    private int hp;

    /**
     * Creates a Food item with the given properties.
     * 
     * @param name
     * @param description
     * @param hp
     * @param value
     */
    public Food(String name, String description, int hp, Gold value) {
        super(name, description, value);
        this.hp = hp;
    }

    /**
     * Gets the HP value that will be applied to the player when the item is used.
     * 
     * @return The HP value.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns a clean string representation of a Food item including the
     * name, description, HP, and Gold count.
     * 
     * @return A string describing the Food.
     */
    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nHp: %s%nValue: %s", getName(), getDescription(), getHp(), gold.getCount());
    }
}
