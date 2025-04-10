    package edu.rit.swen262.domain;

import edu.rit.swen262.service.GameMediator;

/**
 * a representation of a Character within the MUD Game, which has a name, decsription
 * and stats, and is capable of attacking any other Character on the Map.
 * 
 * @author Victor Bovat
 */
public abstract class GameCharacter implements Occupant, java.io.Serializable, Attackable {
    String name;
    String description;
    int health;
    int maxHP;
    int attack;
    int defense;

    private GameMediator mediator;

    /**
     * initializes a {@link GameCharacter} with the given name, description, 
     * and max health, attack, and defense stats. by default, the health
     * stat is set to the full maxHP value.
     * 
     * @param name the name of the character
     * @param description the brief description of the character
     * @param health the health stat of the character
     * @param attack the attack stat of the character
     * @param defense the defense stat of the character
     */
    public GameCharacter(String name, String description, int maxHP, int attack, 
    int defense) {
        this.name = name;
        this.description = description;
        this.health = maxHP;
        this.maxHP = maxHP;
        this.attack = attack;
        this.defense = defense;
    }

    /**
     * {@inheritdoc}
     */
    public void setMediator(GameMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * attacks the other specified {@link GameCharacter} and deducts health from
     * them based upon the initaor's attack stat and the opponent's defense stat.
     * If the calculated damage is more than the opponent's current health, then
     * it will instead be set to zero.
     * 
     * @param opponent the {@link GameCharacter} to be attacked
     */
    public String attack(GameCharacter opponent) {
        int damage = getAttack() - opponent.getDefense();
        opponent.health -= damage;

        if (opponent.health < 0 || opponent.health == 0) {
            opponent.health = 0;
            return opponent.getName() + " has been defeated by " + getName();
        }

        else {
            return getName() + " attacked " + opponent.getName() + " for " + damage;
        }

    }

    /**
     * {@inheritdoc}
     */
    public String takeDamage(int damage) {
        int damageTaken = Math.max(damage - this.defense, 1);

        if (this.health >= damageTaken) {
            this.health -= damageTaken;
        } else {
            this.health = 0;
        }

        return this.name + " took damage for " + damageTaken + " points!";
    }

    /**
     * fetches the name of the {@link GameCharacter}
     * 
     * @return name of the {@link GameCharacter}
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the {@link GameCharacter} to the specified name
     * 
     * @param name the new name of the {@link GameCharacter}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the description of the {@link GameCharacter} to the given String
     * 
     * @param description the new description of the {@link GameCharacter}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getAttack() {
        return this.attack;
    }
    
    public int getDefense() {
        return this.defense;
    }

    public String getDescription() {
        return this.description;
    }
}
