package edu.rit.swen262.domain.DungeonPiece;

import edu.rit.swen262.domain.DirectionalVector;

public class RoomNode {
    private Room room;

    private RoomNode north;
    private RoomNode east;
    private RoomNode south;
    private RoomNode west;
    
    public RoomNode(Room room) {
        this.room = room ;   
    }

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
        
    public RoomNode getConnection(Room room, DirectionalVector dir) {
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

    public Room getRoom() {
        return room;
    }
}
