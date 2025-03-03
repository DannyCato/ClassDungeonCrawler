package edu.rit.swen262.domain;

/**
 * represents the Player Character traveling through the dungeon, extends the 
 * {@link Character}
 */
public class PlayerCharacter extends Character {
    Armor armor;
    Weapon weapon;
    Inventory inventory;
    Gold gold;

    /**
     * initializes a PlayerCharacter with the given name and description. Their
     * maxHP, attack, and defense stats are set to 100, 10, and 0 respectively by default.
     * 
     * @param name the given name of the player
     * @param description the given brief description of the player
     * @param armor the equipped armor
     * @param weapon the equipped weapon
     * @param gold the gold 
     */
    
    public PlayerCharacter(String name, String description) {
        super(name, description, 100, 10, 0);
        this.armor = null;
        this.weapon = null;
        this.inventory = new Inventory(6);
        this.gold = new Gold(0);
    }

    public PlayerCharacter(String name, String description, Armor armor, Weapon weapon, Inventory inventory, Gold gold) {
        super(name, description, 100, 10, 0);
        this.armor = armor;
        this.weapon = weapon;
        this.inventory = inventory;
        this.gold = gold;
    }

}
