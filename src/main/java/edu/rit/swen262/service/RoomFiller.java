package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Exit;
import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.RoomNode;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class RoomFiller {
    //this is so broken dude
        private static final Random rand = new Random();

    public static void fill(Room room) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;

        ArrayList<RoomNode> rns = originalRoom.getRoomNode().getAllConnections();
        for (int i = 0; i < size; i++) {
            double chance = rand.random();
            if (((i < room.width) || ((i % room.width) == (room.width - 1)) || (i > (room.height - 1) * room.width) || ((i % room.width) == 0))
            // && !(i == 0 || i == (room.height - 1 ) * room.width || i == width || i == size - 1
            ) {
                if (i < room.width) {
                    
                }
                DirectionalVector exitDir = room.tileOnEdge(t);
                if (room.tileOnEdge(t) == null) {
                    continue;
                }
                if (rns.get(i) == null) {
                    continue;
                }
                Exit exit = new Exit("", exitDir);
                Tile newT = new Tile(exit);
                room.addTile();
            } 
            
        }
    }

    private static boolean validateRoom(Room room) {
        Tile[] exits = new ArrayList<>(Arrays.asList(DirectionalVector.NORTH, DirectionalVector.EAST, DirectionalVector.SOUTH, DirectionalVector.WEST)).stream().map(dir -> room.getExitTileByDirection(dir)).filter(Objects::nonNull).toArray(Tile[]::new);

        for (Tile exit1 : exits) {
            for (Tile exit2 : exits) {
                if (exit1 != exit2) {
                    Queue<Tile> queue = new PriorityQueue<>();
                    queue.add(exit1);
                    boolean ExitFound = false;
                    while (ExitFound == false) {
                        if (queue.isEmpty()) {
                            return false;
                        }
                        Tile current = queue.poll();
                        if (current == exit2) {
                            ExitFound = true;
                        }
                        Collection<DungeonPiece<Tile>> neighbors = new ArrayList<>();
                        room.getAllAdjacentTiles(current, neighbors);
                        for (DungeonPiece<Tile> neighbor : neighbors) {
                            if (Objects.nonNull(neighbor)) {
                                queue.add((Tile)neighbor);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}