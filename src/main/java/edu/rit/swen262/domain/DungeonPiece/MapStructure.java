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

    public RoomNode addLooseRoom(RoomNode room) {
        return rooms.put(room, room);
    }

    public boolean addRoom(RoomNode key, RoomNode value, DirectionalVector dir) {
        RoomNode room = getRoom(key);
        if (room == null) {
            room = addLooseRoom(key);;
        }
        return room.setConnection(value, dir);
    }

    public RoomNode getRoom(RoomNode key) {
        return rooms.get(key);
    }
}
