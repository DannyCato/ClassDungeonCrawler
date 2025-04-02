package edu.rit.swen262.domain.DungeonPiece;

import edu.rit.swen262.domain.DirectionalVector;

public class RoomNode {
    private DungeonPiece<Room> room;

    private RoomNode north;
    private RoomNode east;
    private RoomNode south;
    private RoomNode west;
    
    public RoomNode(DungeonPiece<Room> room) {
        this.room = room ;   
    }

    /**
     * sets a connection between r and this
     * 
     * @param r {@link RoomNode}
     * @param dir {@link DirectionalVector}
     * @return a boolean of whether the connection was successful or not
     */
    public boolean setConnection(RoomNode r, DirectionalVector dir) {
        switch (dir) {
            case NORTH:
                north = r;
                break;
            case SOUTH:
                south = r;
                break;
            case EAST:
                east = r;
                break;
            case WEST:
                west = r;
                break;
            default:
                return false;
        }
        return true;
    }
    
    /**
     * Get a {@link RoomNode} in a {@link DirectionalVector}
     * @param dir {@link DirectionalVector}
     * @return {@link RoomNode} in direction. Null, if none has been placed yet
     */
    public RoomNode getConnection(DirectionalVector dir) {
        switch (dir) {
            case NORTH:
                return north;
            case SOUTH:
                return south;
            case EAST:
                return east;
            case WEST:
                return west;
            default:
                return null;
        }
    }

    /**
     * Get the {@link DungeonPiece}<{@link Room}> associated with this {@link RoomNode}
     * @return {@link DungeonPiece}<{@link Room}>
     */
    public DungeonPiece<Room> getRoom() {
        return room;
    }
}
