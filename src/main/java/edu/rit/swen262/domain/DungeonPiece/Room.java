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
     * Storage for {@link Tile tiles}. (Recommended to be {@link ArrayList}) <p>
     * 
     * This is a 1D array that is set up to be of size width * height. It can be read in the following comments
     */
    private List<DungeonPiece<Tile>> tiles;
    // Instead of a List<List<DungeonPiece<Tile>>> we can do List<DungeonPiece<Tile>> Here's how:                         | |
    //               Y                               mutliply wanted height by width then add a width offset for equation V V 
    //              0| 0 1 2                                   __0__|__1__|__2__ <-Functionally a Y index       Tile =  wanted height * width of line + width offset        
    //              1| 3 4 5                      real index-> 0 1 2|3 4 5|6 7 8                                     = wanted Y index * width of line + wanted X index 
    //              2| 6 7 8                                   -----+-----+-----
    //               +------ X                      X offset-> 0 1 2|0 1 2|0 1 2
    //                 0 1 2                      

    /**
     * storage for indicies of tiles. Integers so we don't save the same thing twice
     */
    private List<Integer> exitsTiles;


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
    public Room(int width, int height, String description, List<DungeonPiece<Tile>> tiles, List<Integer> exitTiles)
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
     * @param addTile {@link DungeonPiece}<{@link Tile}> a {@link Tile} to add
     * 
     * @return boolean if 
     */
    public boolean addTile(DungeonPiece<Tile> addTile) {
        if (!tiles.contains(addTile)) { // if tiles has addTile
            tiles.add(addTile); // add
            if (((Tile)addTile).isExit()) { // check if addTile is an exit tile
                exitsTiles.add(tiles.indexOf(addTile)); // Add the index of Exit Tiles
            }
            return true;
        }
        return false;
    }
 
    // TODO: Discuss if Room should hold position information about Occupants external of Tile
    // TODO: Figure out how Exiting works because it seems like that should work on a Room or Map level. It's still a little hazy to actually see though
}
