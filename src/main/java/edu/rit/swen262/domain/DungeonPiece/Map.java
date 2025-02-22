package edu.rit.swen262.domain.DungeonPiece;

import java.util.Collection;
import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;

/**
 * A representation of {@link Map} {@link DungeonPiece} a composite of {@link Room Rooms}
 *                         (v LIST YOUR NAME HERE WHEN YOU WORK ON IT v)
 * @authors Danny Catorcini, 
 */
public class Map implements DungeonPiece<Map> {
    // TODO: so tired rn



    // <-----------------------Constructors----------------------->




    // <-----------------------Methods----------------------->

    /**
     * Return a formatted {@link Room} based on RenderRepresenations
     * 
     * @return List<{@link RenderRepresentation}>
     */
    @Override
    public List<RenderRepresentation> render() {
        // TODO: Do what the comment says
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    /**
     * Return the descriptions of the {@link Room} and {@link Tile Tiles}
     * 
     * @return a String of descriptions
     */
    @Override
    public String description() {
        // TODO: do what the comment says
        throw new UnsupportedOperationException("Unimplemented method 'description'");
    }

    /**
     * Returns all the {@link Occupant Occupants} in the {@link Room}
     * 
     * @return Collection<{@link Occupant}> all occupants in the room
     */
    @Override
    public Collection<Occupant> getOccupants() {
        // TODO: create a collection of Occupants (preferably of the same type as the one in Tiles) and append all tiles to it.
        throw new UnsupportedOperationException("Unimplemented method 'getOccupants'");
    }
}