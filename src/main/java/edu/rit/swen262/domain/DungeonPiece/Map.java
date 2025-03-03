package edu.rit.swen262.domain.DungeonPiece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Exit;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;

/**
 * A representation of {@link Map} {@link DungeonPiece} a composite of {@link Room Rooms}
 *                         (v LIST YOUR NAME HERE WHEN YOU WORK ON IT v)
 * @authors Danny Catorcini, 
 */
public class Map implements DungeonPiece<Map>, java.io.Serializable {
    
    private final MapStructure rooms;

    private DungeonPiece<Tile> startTile;

    private final DungeonPiece<Room> startRoom; 
    private DungeonPiece<Room> goal;

    private DungeonPiece<Room> currentRoom;


    // <-----------------------Constructors----------------------->

    public Map(DungeonPiece<Room> root, DungeonPiece<Tile> startTile) {
        this.rooms = new MapStructure();
        this.startRoom = root;
        this.currentRoom = root;

        this.startTile = startTile;
        rooms.addLooseRoom(((Room) root).getRoomNode());
    }

    
    // <-----------------------Methods----------------------->

    /**
     * Return a formatted {@link Room} based on {@link RenderRepresenations}
     * 
     * @return List<{@link RenderRepresentation}>
     */
    @Override
    public List<RenderRepresentation> render() {
        return currentRoom.render();
    }

    /**
     * returns a formatted {@link Room} based on render()
     * 
     * @return List<{@link String}>
     */
    public List<String> structuredRender() {
        ArrayList<String> returnList = new ArrayList<>();
        String s = "";
        List<RenderRepresentation> li = render();
        RenderRepresentation last = null;
        for (RenderRepresentation rr : li) {
            if (last == RenderRepresentation.HWALL && rr == RenderRepresentation.HWALL) {
                returnList.add(s);
                s = new String();
            }
            s += rr.render();
            last = rr;
        }
        returnList.add(s);
        return returnList;
    }

    /**
     * Return the descriptions of the {@link Room} and {@link Tile Tiles}
     * 
     * @return a String of descriptions
     */
    @Override
    public String description() {
        return currentRoom.description();
    }

    /**
     * Returns all the {@link Occupant Occupants} in the {@link Room}
     * 
     * @return Collection<{@link Occupant}> all occupants in the room
     */
    @Override
    public Collection<Occupant> getOccupants() {
        return currentRoom.getOccupants();
    }

    /**
     * adds a {@link Room} to rooms.
     * 
     * @param tile {@link DungeonPiece}<{@link Tile}> a {@link Tile} to add
     */
    public boolean addRoom(DungeonPiece<Room> from, DungeonPiece<Room> to, DirectionalVector dir, boolean isGoal) {
        RoomNode foundFrom = rooms.getRoom(((Room)from).getRoomNode());
        RoomNode foundTo = rooms.getRoom(((Room)to).getRoomNode());
        if (foundFrom == null || foundTo == null ) {
            return false;
        }
        boolean success = rooms.addRoom(foundFrom, foundTo, dir);
        if (success && isGoal) {
            this.goal = to;
        }
        return success;
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

    public boolean canExitRoom(DirectionalVector dir) {
        return (((Room) currentRoom).getRoomNode().getConnection(dir)) != null;
    }

    /**
     * 
     */
    public boolean move(Occupant o, DirectionalVector dir) {
        return ((Room)currentRoom).moveOccupant(o, dir);
    }

    /**
     * exit from {@link DungeonPiece}<{@link Room}> to {@link DungeonPiece}<{@link Room}>
     * 
     * @param o {@link Occupant}
     * @param dir {@link DirectionalVector}
     * @return boolean
     */
    public boolean exitRoom(Occupant o, DirectionalVector dir) {
        Room r = (Room) this.currentRoom;
        if (!r.occupantOnExit(o)) { // if the occupant is on an exit
            return false;
        }
        if (!canExitRoom(dir)) { // if they can exit from the direction given
            return false;
        }
        Tile exitTile = (Tile)r.getTileOfOccpant(o);
        Occupant thisExit = exitTile.getPermanentOccupant();
        if (!(thisExit instanceof Exit)) { // if the permanent occupant is an exit then pass
            return false;
        }
        if (dir != exitTile.getExitDirection()) { // if the exit direction and the given direction are the same
            return false;
        }
        Room otherRoom = (Room) r.getRoomNode().getConnection(dir).getRoom();
        Tile otherTile = (Tile) otherRoom.getExitTileByDirection(DirectionalVector.getOppositeDirection(dir));
        if (otherTile == null) { // if associated tile exists
            return false;
        }
        Exit otherExit = (Exit) otherTile.getPermanentOccupant();
        if (DirectionalVector.getOppositeDirection(otherExit.getExitDirection()) != dir) { // if the opposite of the other Exit's direction is the given direction
            return false;
        }
        exitTile.removeOccupant(o); // finally do process
        otherTile.addOccupant(o);
        currentRoom = otherRoom;
        return true;
    }
    
}