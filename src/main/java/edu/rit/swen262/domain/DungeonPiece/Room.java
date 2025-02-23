package edu.rit.swen262.domain.DungeonPiece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;


/**
 * A representation of {@link Room} {@link DungeonPiece} a composite of {@link Tile Tiles}
 *                         (v LIST YOUR NAME HERE WHEN YOU WORK ON IT v)
 * @authors Danny Catorcini, 
 */
public class Room implements DungeonPiece<Room> {
    
    /**
     * Storage for {@link Tile tiles}. <p>
     * 
     * not sure if this is 1- or 2-dimensional yet. Probably good to discuss before a decision is made
     */
    private List<DungeonPiece<Tile>> tiles;

    /**
     * storage for general classes. unsure how this will effect everything else as of now <p>
     * 
     * same discussion as tiles
     */
    private List<DungeonPiece<Tile>> exitsTiles;


    private final int width;
    private final int height;

    /**
     * a String description of the room. 
     */
    private String description;


    // <-----------------------Constructors----------------------->

    /**
     * Create a Room
     * 
     * @param width int
     * @param height int
     * @param description String
     * @param tiles {@link DungeonPiece}<{@link Tile}>
     * @param exitTiles {@link DungeonPiece}<{@link Tile}> must be different from tiles
     */
    public Room(int width, int height, String description, List<DungeonPiece<Tile>> tiles, List<DungeonPiece<Tile>> exitTiles)
    {
        this.width = width;
        this.height = height;
        this.description = description;
        this.tiles = tiles;
        this.exitsTiles = exitTiles;
    }

    /**
     * Constructor for width, height, and description. Makes ArrayLists for tiles and exitTiles
     * 
     * @param width int
     * @param height int
     * @param description String
     */
    public Room(int width, int height, String description) {
        this(width, height, description, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Constructor for only width and height. default description is "a room" and
     * makes ArrayLists for tiles and exitTiles
     * 
     * @param width int
     * @param height int
     */
    public Room(int width, int height) {
        this(width, height, "a room");
    }


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

    /**
     * testing purposes only
     * 
     * @return String representation of the room to get some general details
     */
    @Override
    public String toString() {
        return "" + width + "x" + height + "room: " + description;
    }

    /**
     * adds a {@link Tile} to tiles. If addedTile is also an exit tile then it will be automatically added to exitTiles
     * 
     * @param tile {@link DungeonPiece}<{@link Tile}> a {@link Tile} to add
     */
    public void addTile(DungeonPiece<Tile> addedTile) {
        // TODO: Decide on a collection type or make one and implement an add method here
        // if the added tile is an exit then automatically add it to exitTiles
        throw new UnsupportedOperationException("Unimplemented method 'addTile'");
    }
 
    // TODO: Discuss if Room should hold position information about Occupants external of Tile
    // TODO: Figure out how Exiting works because it seems like that should work on a Room or Map level. It's still a little hazy to actually see though
}
