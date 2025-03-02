package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class RoomFiller {
    //this is so broken dude
    private static final Random rand = new Rando

    public static void fill(Room room) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;

        for (int i = 0; i < size; i++) {
            Tile t = new Tile();
            if ()
            
            room.addTile();
        }
    }

    private static boolean validateRoom(Room room) {
        Tile[] exits = new ArrayList<>(Arrays.asList(DirectionalVector.NORTH, DirectionalVector.EAST, DirectionalVector.SOUTH, DirectionalVector.WEST)).stream().map(dir -> room.getExitTileByDirection(dir)).filter(Objects::nonNull).toArray(Tile[]::new);

        for (Tile exit1 : exits) {
             for (Tile exit2 : exits) {
                if (exit1 != exit2) {
                    Queue<Tile> queue = new PriorityQueue<>();
                    queue.add(exit);
                    boolean ExitFound = false;
                    while (ExitFound == false && queue.size() > 0) {
                        Tile current = queue.poll();
                        if (current == exit) {
                        Collection<DungeonPiece<Tile>> neighbors = new ArrayList<>();
                        room.getAllAdjacentTiles(current, neighbors);
                        for (DungeonPiece<Tile> neighbor : neighbors) {
                            queue.add((Tile)neighbor);
                        }
                    }
                }
        }
    }
}