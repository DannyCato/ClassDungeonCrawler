package edu.rit.swen262.domain.DungeonPiece;

import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.domain.DirectionalVector;

public class RoomNode implements java.io.Serializable {
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
            case DirectionalVector.NORTH:
                north = r;
                break;
            case DirectionalVector.SOUTH:
                south = r;
                break;
            case DirectionalVector.EAST:
                east = r;
                break;
            case DirectionalVector.WEST:
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
            case DirectionalVector.NORTH:
                return north;
            case DirectionalVector.SOUTH:
                return south;
            case DirectionalVector.EAST:
                return east;
            case DirectionalVector.WEST:
                return west;
            default:
                return null;
        }
    }

    public ArrayList<RoomNode> getAllConnections() {
        ArrayList<RoomNode> conns = new ArrayList<>();
        conns.add(north);
        conns.add(east);
        conns.add(south);
        conns.add(west);
        return conns;
    }

    /**
     * Get the {@link DungeonPiece}<{@link Room}> associated with this {@link RoomNode}
     * @return {@link DungeonPiece}<{@link Room}>
     */
    public DungeonPiece<Room> getRoom() {
        return room;
    }
}
