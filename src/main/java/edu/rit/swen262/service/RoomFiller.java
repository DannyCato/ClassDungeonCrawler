package edu.rit.swen262.service;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class RoomFiller {

    public static void fill(Room room) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;

        for (int i = 0; i < size; i++) {
            room.addTile(new Tile());
        }
    }

    private static boolean validateRoom(Room room) {
        Tile[] exits;
        
= htro
        for (DirectionalVector dir : new ArrayList<>(Arrayss.asList[DirectionalVector.NORTH, DirectionalVector.EAST, DirectionalVector.SOUTH, DirectionalVector.WEST])) {
            System.out.println();
        }
        
        Tile exitTile = room.getExitTileByDirection(dir);
        Queue<Tile> queue = new Queue<>();
        
    }
}