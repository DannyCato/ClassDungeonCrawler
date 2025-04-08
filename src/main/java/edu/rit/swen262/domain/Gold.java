package edu.rit.swen262.domain;

/**
 * Represents Gold, the in game currency of the M.U.D game.
 * Gold is an item, however it acts as its own standalone object
 * where its value is determined by the count.
 * Extends the {@code Item} interface.
 * 
 * @author [Nick F, Ryan M]
 */

public class Gold extends Item {

    /** The quantity of gold the player has. */
    private int count;
    
    /**
     * Creates a Gold item given a quantity.
     * 
     * @param count
     */
    public Gold(int count) {
        super("Gold", "This item is a form of currency.", null);
        this.count = count;
    }

    /**
     * Increases the player's gold by one.
     */
    public void increaseCount() {
        this.count++;
    }

    /**
     * Decreases the player's gold by one, if the player has more than 0 pieces.
     */
    public void decreaseCount() {
        this.count--;
        if (this.count < 0) {
            this.count = 0;
        }
    }

    public void setCount(int num) {
        this.count = num;
        if (this.count < 0) {
            this.count = 0;
        }
    }

    /**
     * Gets the value of the gold (Returns itself.)
     */
    @Override
    public Gold getValue() {
        return this;
    }

    /**
     * Gets the total count of the gold the user has.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns a clean string representation of a Gold item including the
     * name, description, and count.
     * 
     * @return A string describing the Gold.
     */
    @Override
    public String toString() {
        return String.format("%s: %s%nValue: %s", getName(), getDescription(), getCount());
    }
}
