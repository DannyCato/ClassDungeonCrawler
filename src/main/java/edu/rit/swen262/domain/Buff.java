package edu.rit.swen262.domain;

/**
 * Represents a Buff item that will increase the player's stats for a number of turns.
 * Extends the {@code Item} interface.
 * 
 * @author [Nick F, Ryan M]
 */

public class Buff extends Item {

    /** The duration of the buff for a certain number of rounds */
    private int duration;
    /** The attack boost that will be provided */
    private int attack;
    /** The defense value that will be provided */
    private int defense;
    /** The HP value that will be provided */
    private int hp;

    /**
     * Creates the Buff item with the following properties.
     * 
     * @param name The name of the buff item.
     * @param description The description of the buff item.
     * @param duration The number of turns that the buff will last.
     * @param attack The attack increase of the Buff.
     * @param defense The defense increase of the Buff.
     * @param hp The HP increase of the Buff.
     * @param value The value in Gold that the Buff is worth.
     */
    public Buff(String name, String description, int duration, int attack, int defense, int hp, Gold value) {
        super(name, description, value);
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.duration = duration;
    }

    /**
     * Gets the duration of the buff.
     * 
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Decreases the amount of turns a buff will last for.
     * If the duration falls below 0, it will be reset to 0.
     */
    public void decreaseDuration() {
        this.duration--;
        if (this.duration < 0) {
            this.duration = 0;
        }
    }

    /**
     * Gets the attack boost given by the Buff.
     * 
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Gets the defense boost given by the Buff.
     * 
     * @return
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Gets the HP boost given by the Buff.
     * 
     * @return
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns a clean string representation of a buff item including name,
     * description, duration, attack, defense, HP, and gold value.
     * @return A string describing the Buff.
     */
    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nDuration: %s%nAttack: %s%nDefense: %s%nHp: %s%nValue: %s", getName(), getDescription(), getDuration(), getAttack(), getDefense(), getHp(), gold.getCount());
    }
}
