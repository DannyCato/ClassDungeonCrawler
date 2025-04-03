package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Enemy;
import edu.rit.swen262.domain.Exit;
import edu.rit.swen262.domain.Obstacle;
import edu.rit.swen262.domain.Occupant;
import edu.rit.swen262.domain.DungeonPiece.DungeonPiece;
import edu.rit.swen262.domain.DungeonPiece.Room;
import edu.rit.swen262.domain.DungeonPiece.RoomNode;
import edu.rit.swen262.domain.DungeonPiece.Tile;

public class RoomFiller {
    private static final Random rand = new Random();

    public static void fill(Room room, double obstacleChance) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;
        
        ArrayList<RoomNode> rns = originalRoom.getRoomNode().getAllConnections();
        for (int i = 0; i < size; i++) {
            int currentWidth = i % room.width;
            int currentHeight = i / room.width;

            int midWidth = (int) Math.ceil(room.width/2.0) ;
            int midHeight = (int) Math.ceil(room.height/2.0) ;
            
            Tile t = new Tile();
            double chance = rand.nextDouble();
            if (i < room.width && rns.get(0) != null) {
                if (currentWidth == midWidth) {
                    t = new Tile(new Exit("", DirectionalVector.NORTH));
                    t.setExit(true);
                }
            } else if ((i % room.width) == (room.width - 1) && rns.get(1) != null) {
                if (currentHeight == midHeight) {
                    t = new Tile(new Exit("", DirectionalVector.EAST));
                    t.setExit(true);
                }
            } else if (i > (room.height - 1) * room.width && rns.get(2) != null) {
                if (currentWidth == midWidth) {
                    t = new Tile(new Exit("", DirectionalVector.SOUTH));
                    t.setExit(true);
                }
            } else if ((i % room.width) == 0 && rns.get(3) != null) {
                if (currentHeight == midHeight) {
                    t = new Tile(new Exit("", DirectionalVector.WEST));
                    t.setExit(true);
                }
            } else {
                Occupant o = null;
                if (chance < obstacleChance) {
                    if (rand.nextBoolean()) {
                        o = new Obstacle("");
                    } else {
                        o = generateEnemy();
                    }
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

    private static Occupant generateEnemy() {
        List<String> names = Arrays.asList("Zombie", "Vampire", "Werewolf", "Ghost", "Goblin",
                                                "Orc", "Troll", "Skeleton", "Mummy", "Golem",
                                                "Lich", "Demon", "Dragon", "Wraith", "Kraken",
                                                "Hydra", "Griffin", "Minotaur", "Chimera", "Harpy");
        String name = names.get(rand.nextInt(names.size()));
        return new Enemy(name, rand.nextInt(101), rand.nextInt(50), rand.nextInt(50));
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