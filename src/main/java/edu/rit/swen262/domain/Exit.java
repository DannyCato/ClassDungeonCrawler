package edu.rit.swen262.domain;

/**
 * A representation of the {@link Exit} between two {@link Tile} {@link DungeonPiece}s <p>
 * 
 * @authors Eric Manning,
 */
public class Exit implements Occupant {
    private static final RenderRepresentation RENDER_REPRESENTATION = RenderRepresentation.EXIT;
    private static final boolean STACKABLE = true;
    private String description;
    private ExitDirection exitDirection;

    public Exit(String description, ExitDirection exitDirection) {
        this.description = description;
        this.exitDirection = exitDirection;
    }

    /**
     * Return the {@link RenderRepresentation} for the {@link Exit} which is always an 'X'
     * 
     * @return {@link RenderRepresentation} to be displayed
     */
    @Override
    public RenderRepresentation render() {
        return Exit.RENDER_REPRESENTATION;
    }

    /**
     * Return the description of the {@link Exit}
     * 
     * @return the description of the {@link Exit}
     */
    @Override
    public String description() {
        return description;
    }
}
