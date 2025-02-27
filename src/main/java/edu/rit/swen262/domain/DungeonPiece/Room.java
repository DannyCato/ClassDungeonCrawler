package edu.rit.swen262.domain.DungeonPiece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.ExitDirection;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;


/**
 * A representation of {@link Room} {@link DungeonPiece} a composite of {@link Tile Tiles}
 *                         
 * @authors Danny Catorcini 
 */
public class Room implements DungeonPiece<Room> {
    
    /**
     * Storage for {@link Tile tiles}. (Recommended to be {@link ArrayList}) <p>
     * 
     * This is a 1D array that is set up to be of size width * height. It can be read in the following comments
     */
    private List<DungeonPiece<Tile>> tiles;
    // Instead of a List<List<DungeonPiece<Tile>>> we can do List<DungeonPiece<Tile>> Here's how:            | |
    //  Y                               mutliply wanted height by width then add a width offset for equation V V 
    // 0| 0 1 2                                   __0__|__1__|__2__ <-Functionally a Y index       Tile =  wanted height * width of line + width offset        
    // 1| 3 4 5                      real index-> 0 1 2|3 4 5|6 7 8                                     = wanted Y index * width of line + wanted X index 
    // 2| 6 7 8                                   -----+-----+-----
    //  +------ X                      X offset-> 0 1 2|0 1 2|0 1 2
    //    0 1 2                      

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
        List<RenderRepresentation> room = new ArrayList<>();
        room.add(RenderRepresentation.CORNER); // top left corner

        for (int i = 0; i < this.width; i++) { // top walls
            room.add(RenderRepresentation.HWALL);
        }
        room.add(RenderRepresentation.CORNER); // top right corner

        for (int i = 0; i < this.height; i++) {
            room.add(RenderRepresentation.VWALL); // wall to the left
            for (int j = 0; j < this.width; j++) {
                Tile currentTile = (Tile) tiles.get(i * width + j); // get the tile at the current i, j
                List<RenderRepresentation> tileRender = currentTile.render();
                room.add(tileRender.get(tileRender.size() - 1)); // add the top priority occupant render

                if (currentTile.isExit()) {
                    ExitDirection exitDirection = currentTile.getExitDirection();
                    int exitIndex = room.size() - 1; // Current tile's position in room list

                    // Determine where to place EXIT based on direction
                    switch (exitDirection) {
                        case NORTH:
                            room.set(exitIndex - (this.width + 2), RenderRepresentation.EXIT);
                            break;
                        case SOUTH:
                            room.set(exitIndex + (this.width + 2), RenderRepresentation.EXIT);
                            break;
                        case EAST:
                            room.set(exitIndex + 1, RenderRepresentation.EXIT);
                            break;
                        case WEST:
                            room.set(exitIndex - 1, RenderRepresentation.EXIT);
                            break;
                    }
                }
            }
            room.add(RenderRepresentation.VWALL); // wall to the right
        }

        room.add(RenderRepresentation.CORNER); // bottom left corner
        for (int i = 0; i < this.width; i++) { // bottom walls
            room.add(RenderRepresentation.HWALL);
        }
        room.add(RenderRepresentation.CORNER); // bottom right corner

        return room;
    }

    /**
     * Return the descriptions of the {@link Room} and {@link Tile Tiles}
     * 
     * @return a String of descriptions
     */
    @Override
    public String description() {
        LinkedList<String> descs = new LinkedList<>();
        descs.add(description);
        for ( DungeonPiece<Tile> t : tiles ) {
            descs.add(t.description());
        }
        descs.add("and " + descs.pop());
        return String.join(", ", descs);
    }

    /**
     * Returns all the {@link Occupant Occupants} in the {@link Room}
     * 
     * @return Collection<{@link Occupant}> all occupants in the room
     */
    @Override
    public Collection<Occupant> getOccupants() {
        Collection<Occupant> newCol = DungeonPiece.getNewCollectionOfType(tiles.get(0).getOccupants());
        for ( DungeonPiece<Tile> t : tiles ) {
            newCol.addAll(t.getOccupants());
        }
        return newCol;
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
     * @return boolean. true if the tile was added
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
    
    /**
     * Method to move {@link Occupant} from one {@link Tile} in the direction described by a {@link DirectionalVector}
     * 
     * @param o {@link Occupant} to be moved
     * @param dir a {@link DirectionalVector}. direction to be moved in
     * @return boolean. True if Occupant was moved
     */
    public boolean moveOccupant(Occupant o, DirectionalVector dir) {
        int index = 0;
        Tile t = null;
        for (DungeonPiece<Tile> itfTile : tiles) { // find Tile that contains o in transientOccupant
            t = (Tile) itfTile;
            if (t.containsTransientOccupantOf(o)) {
                break;
            }
            ++index;
        }
        if (t == null) { // if a tile with TransientOccupant o is not found
            return false;
        }
        int adjIndex = getAdjactentTileInDir(index, dir);
        if (adjIndex == index) { // if getAdjactentTileInDir failed
            return false;
        }
        Tile adjT = (Tile) tiles.get(adjIndex);
        if (!adjT.isStackable()) { // if adjacent Tile is not stackable
            return false;
        }
        t.removeOccupant(o); // finally do process
        adjT.addOccupant(o);
        return true;
    }

    /**
     * Helper method to find a {@link Tile} in the direction described by a {@link DirectionalVector}
     * 
     * @param index the index of {@link Tile} in tiles
     * @param dir {@link DirectionalVector}. the direction to find the adjacent in
     * 
     * @return the index of the adjacent {@link Tile}. Returns param index if it failed
     */
    private int getAdjactentTileInDir(int index, DirectionalVector dir) {
        if ((index % width == 0 && dir.x == -1 ) || (index % width == width - 1 && dir.x == 1) // Check if moving on X would wrap
            || (index < width && dir.y == -1) || (index > ((height - 1) * width) && dir.y == 1)) { // Check if moving on Y would go out of bounds
            return index;
        }
        return index + ((dir.y * width) + dir.x); // adjacent tile formula
    }
    // TODO: Figure out how Exiting works because it seems like that should work on a Room or Map level. It's still a little hazy to actually see though
}
