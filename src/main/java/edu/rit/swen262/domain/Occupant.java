package edu.rit.swen262.domain;

import edu.rit.swen262.domain.DungeonPiece.Tile;

/**
 * Defines the interface for {@link Occupant Occupants} to be on {@link Tile Tiles} <p>
 * 
 * @author Danny Catorcini
 */
public interface Occupant {
    //Try to edit this as little as possible
    
    /**
     * Give a char to be displayed for CLI
     * 
     * @return a char representing the {@link Occupant}
     */
    public RenderRepresentation render();

    /**
     * Return a description of the {@link Occupant} to be used for full descriptions of {@link DungeonPiece DungeonPieces}
     * 
     * @return a description of the {@link Occupant}
     */
    public String description();
}
