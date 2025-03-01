package edu.rit.swen262.domain;

/**
 * a representation of a Character within the MUD Game, which has a name, decsription
 * and stats, and is capable of attacking any other Character on the Map.
 * 
 * @author Victor Bovat
 */
public abstract class Character implements Occupant, java.io.Serializable {
    String name;
    String description;
    int health;
    int maxHP;
    int attack;
    int defense;

    /**
     * initializes a {@link Character} with the given name, description, 
     * and max health, attack, and defense stats. by default, the health
     * stat is set to the full maxHP value.
     * 
     * @param name the name of the character
     * @param description the brief description of the character
     * @param health the health stat of the character
     * @param attack the attack stat of the character
     * @param defense the defense stat of the character
     */
    public Character(String name, String description, int maxHP, int attack, 
    int defense) {
        this.name = name;
        this.description = description;
        this.health = maxHP;
        this.maxHP = maxHP;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * attacks the other specified {@link Character} and deducts health from
     * them based upon the initaor's attack stat and the opponent's defense stat.
     * If the calculated damage is more than the opponent's current health, then
     * it will instead be set to zero.
     * 
     * @param opponent the {@link Character} to be attacked
     */
    public void attack(Character opponent) {
        /* TO-DO: implement one-sided attack from character -> opponent
        and deduct the correct health amt based upon atk and def */ 
    }

    /**
     * fetches the name of the {@link Character}
     * 
     * @return name of the {@link Character}
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the {@link Character} to the specified name
     * 
     * @param name the new name of the {@link Character}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the description of the {@link Character} to the given String
     * 
     * @param description the new description of the {@link Character}
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
