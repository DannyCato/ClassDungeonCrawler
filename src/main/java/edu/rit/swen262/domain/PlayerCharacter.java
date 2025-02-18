package edu.rit.swen262.domain;

public class PlayerCharacter extends Character {
    /**
     * initializes a PLayerCharacter with the given name and description. Their
     * health, attack, and defense stats are set to 100, 10, and 0 respectively by default.
     * 
     * @param name the given name of the player
     * @param description the given brief description of the player
     */
    public PlayerCharacter(String name, String description) {
        super(name, description, 100, 10, 0);
    }
}
