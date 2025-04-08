package edu.rit.swen262.domain.DungeonPiece;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Exit;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.RenderRepresentation;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameState;

/**
 * A representation of {@link Map} {@link DungeonPiece} a composite of {@link Room Rooms}
 *
 * @authors Danny Catorcini, Eric Manning
 */
public class Map implements DungeonPiece<Map>, java.io.Serializable {
    
    private final MapStructure rooms;

    private DungeonPiece<Tile> startTile;

    private final DungeonPiece<Room> startRoom; 
    private DungeonPiece<Room> goal;

    private DungeonPiece<Room> currentRoom;

    private boolean goalReached = false;

    private Collection<GameObserver> gos = new HashSet<>() ;

    // <-----------------------Constructors----------------------->

    public Map(DungeonPiece<Room> root) {
        this.rooms = new MapStructure();
        this.startRoom = root;
        this.currentRoom = root;

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
     * @return {@link String}
     */
    public String structuredRender() {
        String s = "";
        HashSet<RenderRepresentation> linebreakTiles = new HashSet<>(); // <- What does this do??
        List<RenderRepresentation> li = render();
        RenderRepresentation last = null;
        for (RenderRepresentation rr : li) {
            if ((last == RenderRepresentation.VWALL && rr == RenderRepresentation.VWALL) 
            || (last == RenderRepresentation.CORNER && rr == RenderRepresentation.VWALL)
            || (last == RenderRepresentation.VWALL && rr == RenderRepresentation.CORNER)) {
                s += "\n";
            }
            s += rr.render();
            last = rr;
        }
        return s;
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
     * @param from {@link DungeonPiece}<{@link Room}>
     * @param to {@link DungeonPiece}<{@link Room}>
     * @param dir {@link DirectionalVector}
     * @param isGoal boolean
     * @return boolean if Room adding was a success
     */
    public boolean addRoom(DungeonPiece<Room> from, DungeonPiece<Room> to, DirectionalVector dir, boolean isGoal) {
        RoomNode foundFrom = rooms.getRoom(((Room)from).getRoomNode());
        if (foundFrom == null) {
            return false;
        }
        boolean success = rooms.addRoom(foundFrom, ((Room)to).getRoomNode() , dir);
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

    /**
     * Checks if there is a connection from the current {@link Room} in the specified {@link DirectionalVector}.
     *
     * @param dir {@link DirectionalVector} indicating the direction to check for an exit
     * @return boolean indicating whether an exit exists in the given direction
     */
    public boolean canExitRoom(DirectionalVector dir) {
        return (((Room) currentRoom).getRoomNode().getConnection(dir)) != null;
    }

    /**
     * Moves an {@link Occupant} within the current {@link Room} in the specified {@link DirectionalVector}.
     * If the occupant is moved onto an exit {@link Tile}, an attempt is made to exit the room.
     * 
     * @param o {@link Occupant} to be moved
     * @param dir {@link DirectionalVector} indicating the direction to move
     * @return boolean indicating whether the move was successful
     */
    public boolean move(Occupant o, DirectionalVector dir) {
        Tile t = ((Room)currentRoom).moveOccupant(o, dir);
        if (t != null && t.isExit()) {
            exitRoom(o, t.getExitDirection());
        };
        return t != null;
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
        Tile exitTile = (Tile)r.getTileOfOccupant(o);
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
        bindNewGameObserversInRoom();
        if (playerOnGoal()) {
            this.goalReached = true;
        }
        return true;
    }

    /**
     * Finds the first stackable {@link Tile} in the {@link Room} given by {@link #setStartRoom(DungeonPiece)}
     * and returns it. This is the tile that the player should start on.
     * 
     * @return the first stackable {@link Tile} in the {@link Room} given by {@link #setStartRoom(DungeonPiece)}
     */
    public DungeonPiece<Tile> startUp() {
        int i = 0;
        startTile = ((Room)startRoom).getTileByIndex(i);
        while ( !( ((Tile)startTile).isStackable() )) {
            i++;
            startTile = ((Room)startRoom).getTileByIndex(i);
        }
        return startTile;
    }

    /**
     * This binds new {@link GameObserver GameObservers} that work on a {@link Room}-Level
     */
    public void bindNewGameObserversInRoom() {
        GameState gs = GameState.getGameStateByMap(this) ;
        for (GameObserver go : gos) {
            gs.deregister(go);
        }
        for (Occupant o : getOccupants()) {
            if (o instanceof GameObserver) {
                gs.register((GameObserver) o);
            }
        }        
    }

    /**
     * Checks if the player has reached the goal {@link Room}.
     *
     * @return boolean indicating whether the current room is the goal room
     */
    private boolean playerOnGoal() {
        return currentRoom == goal;
    }

    /**
     * Sets the goal {@link Room} in the map.
     * 
     * @param goal the {@link DungeonPiece}<{@link Room}> to be set as the goal
     */
    public void setGoal(DungeonPiece<Room> goal) {
        this.goal = goal;
    }
}