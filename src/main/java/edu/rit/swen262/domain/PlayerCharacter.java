package edu.rit.swen262.domain;

/**
 * represents the Player Character traveling through the dungeon, extends the 
 * {@link Character}
 */
public class PlayerCharacter extends Character {
    /**
     * initializes a PlayerCharacter with the given name and description. Their
     * maxHP, attack, and defense stats are set to 100, 10, and 0 respectively by default.
     * 
     * @param name the given name of the player
     * @param description the given brief description of the player
     */
    public PlayerCharacter(String name, String description) {
        super(name, description, 100, 10, 0);
    }

    /**
     * {@inheritDoc}
     */
    public String description() {
        return this.description;
    }

    /**
     * {@link inheritDoc}
     */
    public RenderRepresentation render() {
        return RenderRepresentation.CHARACTER;
    }
}
