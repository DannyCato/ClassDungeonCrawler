package edu.rit.swen262.domain;

// TODO: THINK OF A DIFFERENT IDEA FOR THIS BC THIS OFFERS POOR SCALABILITY
// Honestly maybe it's fine. Was thinking about implementing comparable to keep
// this enum responsible for handling its own values

/**
 * Enum to represent the basic ways an object can be rendered
 * 
 * This does not seem like it is the correct way to do this in
 * hindsight, so we should figure something out
 * 
 * 
 * @author Danny Catorcini
 */
public enum RenderRepresentation {
    CHARACTER('c', 10),
    ENEMY('e', 5),
    EXIT('X'),
    HIDDENTRAP('.'),
    TRAP('T'),
    OBSTACLE('B'),
    CHEST('C'),
    EMPTY('.', -1),
    VWALL('|', 100),
    HWALL('-', 100),
    CORNER('+', 1000);

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
