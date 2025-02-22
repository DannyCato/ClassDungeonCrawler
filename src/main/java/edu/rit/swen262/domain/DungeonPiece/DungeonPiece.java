package edu.rit.swen262.domain.DungeonPiece;

import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;

/**
 * Defines the interface for a physical piece of a dungeon
 * 
 * @author Danny Catorcini
 */
public interface DungeonPiece<T> {
    /**
     * Give a character to be displayed
     * 
     */
    public RenderRepresentation render();

    /**
     * Description of a Dungeon Piece
     * 
     */
    public String description();

    /**
    * Return a list of all Occupants
    * 
    */
    public List<Occupant> getOccupants();
}
