package edu.rit.swen262.domain;

public abstract class Character {
    String name;
    String description;
    int health;
    int attack;
    int defense;

    /**
     * initializes a Character with the given name, description, 
     * and health, attack, and defense stats
     * 
     * @param name the name of the character
     * @param description the brief description of the character
     * @param health the health stat of the character
     * @param attack the attack stat of the character
     * @param defense the defense stat of the character
     */
    public Character(String name, String description, int health, int attack, 
    int defense) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public void attack(Character opponent) {
        /* TO-DO: implement one-sided attack from character -> opponent
        and deduct the correct health amt based upon atk and def */ 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
