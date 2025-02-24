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
    
    private List<DungeonPiece<Room>> rooms;

    private DungeonPiece<Tile> startTile;

    private DungeonPiece<Room> startRoom; 
    private DungeonPiece<Room> goal;

    private DungeonPiece<Room> currentRoom;


    // <-----------------------Constructors----------------------->

    public Map(List<DungeonPiece<Room>> rooms) {
        this.rooms = rooms;
    }

    
    // <-----------------------Methods----------------------->

    /**
     * Return a formatted {@link Room} based on {@link RenderRepresenations}
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

    /**
     * adds a {@link Room} to rooms.
     * 
     * @param tile {@link DungeonPiece}<{@link Tile}> a {@link Tile} to add
     */
    public boolean addRoom(DungeonPiece<Room> room) {
        // TODO: Decide on a collection type or make one and implement an add method here
        throw new UnsupportedOperationException("Unimplemented method 'addRoom'");
    }

    /**
     * returns the {@link Room current Room} that the character is in <p>
     * 
     * This will need to change to work with mulitple users
     * 
     * @return {@link DungeonPiece}<{@link Room}>
     */
    public DungeonPiece<Room> getCurrentRoom() {
        return currentRoom;
    }
    
}