package edu.rit.swen262.domain;

import java.util.Comparator;

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
public enum RenderRepresentation implements Comparator<RenderRepresentation> {
    CHARACTER('c', false, 10),
    ENEMY('e', false, 5),
    EXIT('X'),
    OBSTACLE('B', false),
    CHEST('C'),
    EMPTY('.', Integer.MIN_VALUE),
    HIDDENTRAP(EMPTY.representation, 1),
    TRAP('T'),
    VWALL('|', 100),
    HWALL('-', 100),
    CORNER('+', 1000);

    /**
     * Holds the character as listed above
     */
    public final char representation;

    /**
     * If the occupant allows others occupants to move to
     */
    public final boolean stackable;

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
    private RenderRepresentation(char representation, boolean stackable, int priority) {
        this.representation = representation;
        this.stackable = stackable;
        this.priority = priority;
    }

    private RenderRepresentation(char representation, boolean stackable) {
        this(representation, stackable, 0);
    }

    private RenderRepresentation(char representation, int priority) {
        this(representation, true, priority);
    }

    /**
     * Builds RenderRepresentation. automatically sets priority to 0
     * 
     * @param representation char that holds what the object will like on camera
     */
    private RenderRepresentation(char representation) {
        this(representation, true, 0);
    }

    public char render() {
        return this.representation;
    }

    /**
     * Compare two RenderRepresentations
     * 
     * @param thisRR the first object to compare
     * @param otherRR the second object to compare
     * @return int. -1 if less, 0 if equal, 1 if greater
     */
    @Override
    public int compare(RenderRepresentation thisRR, RenderRepresentation otherRR) {
        return Integer.compare(thisRR.priority, otherRR.priority);
    }
}
