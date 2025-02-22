package edu.rit.swen262.domain.DungeonPiece;

import java.util.Collection;
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
     * Give a list of {@link RenderRepresentation characters} to be displayed
     * 
     * @return list of {@link RenderRepresentation} to be displayed 
     */
    public List<RenderRepresentation> render();

    /**
     * Description of a Dungeon Piece
     * 
     * @return String to be displayed
     */
    public String description();

    /**
    * Return a list of all {@link Occupant Occupants}
    * 
    @return list of {@link Occupant Occupants} to be displayed 
    */
    public Collection<Occupant> getOccupants();
}
