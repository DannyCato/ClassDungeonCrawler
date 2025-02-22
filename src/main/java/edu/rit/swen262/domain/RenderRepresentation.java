package edu.rit.swen262.domain;

/**
 * Enum to represent the basic ways an object can be rendered
 * 
 * This does not seem like it is the correct way to do this in
 * hindsight, so we should figure something out
 * 
 * TODO: THINK OF A DIFFERENT IDEA FOR THIS
 * 
 * @author Danny Catorcini
 */
public enum RenderRepresentation {
    CHARACTER('c'),
    ENEMY('e'),
    EXIT('X'),
    TRAP('T'),
    OBSTACLE('B'),
    CHEST('C');

    /**
     * Holds the character as listed above
     */
    public final char representation;

    /**
     * Builds RenderRepresentation
     * 
     * @param representation char that holds what the object will like on camera
     */
    private RenderRepresentation(char representation) {
        this.representation = representation;
    }
}
