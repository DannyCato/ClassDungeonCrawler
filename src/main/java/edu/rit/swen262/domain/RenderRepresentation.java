package edu.rit.swen262.domain;

/**
 * Enum to represent the basic ways an object can be rendered
 * 
 * This does not seem like it is the correct way to do this in
 * hindsight, so we should figure something out
 * 
 * TODO: THINK OF A DIFFERENT IDEA FOR THIS BC THIS OFFERS NO SCALABILITY
 * 
 * @author Danny Catorcini
 */
public enum RenderRepresentation {
    CHARACTER('c', 10),
    ENEMY('e', 5),
    EXIT('X'),
    TRAP('T'),
    OBSTACLE('B'),
    CHEST('C'),
    EMPTY('.', -1);

    /**
     * Holds the character as listed above
     */
    public final char representation;

    /**
     * Which character should be rendered on top <p>
     * 
     * Higher priority means more important
     */
    public final int priority;

    /**
     * Builds RenderRepresentation
     * 
     * @param representation char that holds what the object will like on camera
     * @param priority int that sets the priority when rendered
     */
    private RenderRepresentation(char representation, int priority) {
        this.representation = representation;
        this.priority = priority;
    }

    /**
     * Builds RenderRepresentation. automatically sets priority to 0
     * 
     * @param representation char that holds what the object will like on camera
     */
    private RenderRepresentation(char representation) {
        this.representation = representation;
        this.priority = 0;
    }

    public char render() {
        return this.representation;
    }
}
