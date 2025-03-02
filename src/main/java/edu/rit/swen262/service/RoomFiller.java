package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Exit;
import edu.rit.swen262.domain.Obstacle;
import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.RoomNode;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class RoomFiller {
    private static final Random rand = new Random();

    /**
     * function to nudge the {@link Exit} to be more toward the center of a room
     *  
     * @param current int 
     * @param val int
     * @return the chance it does
     */
    private static double func(int current, int val) {
        double oper = 1.0/(2*val) * current;
        if (current < val/2.0) {
            return oper;
        } else {
            return 1 - oper;
        }
    }

    public static void fill(Room room, double obstacleChance) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;
        
        boolean isExitPut[] = new boolean[4];
        ArrayList<RoomNode> rns = originalRoom.getRoomNode().getAllConnections();
        for (int i = 0; i < size; i++) {
            int currentWidth = i % room.width;
            int currentHeight = i / room.width;
            
            Tile t = null;
            double chance = rand.nextDouble();
            if (i < room.width && rns.get(0) != null) {
                if (isExitPut[0]) {
                    continue;
                }
                double thisChance = func(currentWidth, room.width);
                Exit e = null;
                if (chance < thisChance) {
                    e = new Exit("", DirectionalVector.NORTH);
                }
                t = new Tile(e);
                isExitPut[0] = true;
            } else if ((i % room.width) == (room.width - 1) && rns.get(1) != null) {
                if (isExitPut[1]) {
                    continue;
                }
                double thisChance = func(currentHeight, room.height);
                Exit e = null;
                if (chance < thisChance) {
                    e = new Exit("", DirectionalVector.EAST);
                }
                t = new Tile(e);
                isExitPut[1] = true;
            } else if (i > (room.height - 1) * room.width && rns.get(2) != null) {
                if (isExitPut[2]) {
                    continue;
                }
                double thisChance = func(currentWidth, room.width);
                Exit e = null;
                if (chance < thisChance) {
                    e = new Exit("", DirectionalVector.SOUTH);
                }
                t = new Tile(e);
                isExitPut[2] = true;
            } else if ((i % room.width) == 0 && rns.get(3) != null) {
                if (isExitPut[3]) {
                    continue;
                }
                double thisChance = func(currentHeight, room.height);
                Exit e = null;
                if (chance < thisChance) {
                    e = new Exit("", DirectionalVector.WEST);
                }
                t = new Tile(e);
                isExitPut[3] = true;
            } else {
                Obstacle o = null;
                if (chance < obstacleChance) {
                    o = new Obstacle("");
                }
                t = new Tile(o);
            }
            room.addTile(t);
        }

        if (!validateRoom(room)) {
            fill(room, obstacleChance);
        }

        for (int i = 0; i < size; i++) {
            originalRoom.addTile(room.getTileByIndex(i));
        } 
    }

    private static boolean validateRoom(Room room) {
        Tile[] exits = new ArrayList<>(DirectionalVector.cardinals).stream().map(dir -> room.getExitTileByDirection(dir)).filter(Objects::nonNull).toArray(Tile[]::new);

        for (Tile exit1 : exits) {
            for (Tile exit2 : exits) {
                if (exit1 != exit2) {
                    Queue<Tile> queue = new LinkedList<>();
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