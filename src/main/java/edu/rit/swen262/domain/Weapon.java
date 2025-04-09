package edu.rit.swen262.domain;

/**
 * Represents a {@link Weapon} item that boosts {@link PlayerCharacter player's} 
 * attack when used (which automatically equips it into the weapon slot).
 * Extends the {@link Item} interface.
 * 
 * @author Nick F, Ryan M
 */
public class Weapon extends Item {

    private int attack;

    /**
     * creates a new weapon with the given name, decsription, attack stat, 
     * and {@link Gold gold} value
     * 
     * @param name the name of the weapon
     * @param description the description of the weapon
     * @param attack the attack stat of the weapon
     * @param value the gold value of the weapon
     */
    public Weapon(String name, String description, int attack, Gold value) {
        super(name, description, value);
        this.attack = attack;
    }

    /**
     * fetches the weapon's attack statistic
     * 
     * @return the attack stat
     */
    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        Gold gold = getValue();
        return String.format("%s: %s%nAttack: %s%nValue: %s", getName(), getDescription(), getAttack(), gold.getCount());
    }
}
