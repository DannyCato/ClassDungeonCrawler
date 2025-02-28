package edu.rit.swen262.domain.DungeonPiece;

import java.util.HashMap;

import edu.rit.swen262.domain.DirectionalVector;

/**
 * Helper Class to manage the layout of Map
 */
public class MapStructure {
    private HashMap<RoomNode, RoomNode> rooms;

    public MapStructure() {
        this.rooms = new HashMap<>();
    }

    /**
     * Adds a room with no connection to the map
     * 
     * @param room {@link RoomNode}
     * @return {@link RoomNode} that was put in
     */
    public RoomNode addLooseRoom(RoomNode room) {
        return rooms.put(room, room);
    }

    /**
     * Connects two {@link RoomNode RoomNodes} together if possible. Adds rooms if they are not alread in the map
     * 
     * @param key {@link RoomNode} The room ideally already in the map
     * @param value {@link RoomNode} The room to be connected
     * @param dir {@link DirectionalVector} direction to be connected
     * @return boolean. True if both rooms could connect, otherwise false
     */
    public boolean addRoom(RoomNode key, RoomNode value, DirectionalVector dir) {
        RoomNode keyRoom = getRoom(key);
        if (keyRoom == null) {
            keyRoom = addLooseRoom(key);
        }
        RoomNode valueRoom = getRoom(value);
        if (valueRoom == null) {
            valueRoom = addLooseRoom(valueRoom);
        }
        if (!valueRoom.setConnection(keyRoom, DirectionalVector.getOppositeDirection(dir))) {
            return false;
        } 
        return keyRoom.setConnection(valueRoom, dir);
    }

    /**
     * Get a {@link RoomNode} from the {@link MapStructure} {@link HashMap} storage entity
     * 
     * @param key {@link RoomNode}
     * @return a {@link RoomNode} if it could be found. Otherwise null
     */
    public RoomNode getRoom(RoomNode key) {
        return rooms.get(key);
    }
}
