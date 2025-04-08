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
    private static final Random RAND = RandomInstance.instance();

/**
 * Generates an exit {@link Tile} if the current position matches the midpoint.
 *
 * @param current the current position to check against the midpoint
 * @param mid the midpoint to compare the current position against
 * @param exit the {@link DirectionalVector} that specifies the direction of the exit
 * @return a {@link Tile} with an {@link Exit} if the current position is the midpoint, otherwise null
 */

    private static Tile exitGen(int current, int mid, DirectionalVector exit) {
        Tile t = null ;
        if (current == mid) {
            t = new Tile(new Exit("", exit));
            t.setExit(true);
        }
        return t;
    }

    /**
     * Fills the room with tiles. The tiles are either an exit if it is at the midpoint of the room in the direction of the exit, or a tile with an optional obstacle or enemy.
     * The obstacle chance is the probability that a tile will have an obstacle or enemy instead of being empty.
     * If the room does not have any exits, the method will be called recursively until the room is filled with tiles that have exits.
     * The generated tiles are then added to the original room.
     * @param room the room to fill
     * @param obstacleChance the probability that a tile will have an obstacle or enemy
     */
    public static void fill(Room room, double obstacleChance) {
        Room originalRoom = room; 
        room = new Room(room.width, room.height);
        int size = room.height * room.width;
        
        ArrayList<RoomNode> rns = originalRoom.getRoomNode().getAllConnections();
        int midWidth = (int) Math.ceil(room.width/2.0) ;
        int midHeight = (int) Math.ceil(room.height/2.0) ;
        
        for (int i = 0; i < size; i++) {
            int currentWidth = i % room.width;
            int currentHeight = i / room.width;

            Tile t = new Tile();
            double chance = RAND.nextDouble();
            if (i < room.width && rns.get(0) != null) {
                t = exitGen(currentWidth, midWidth, DirectionalVector.NORTH) ;

            } else if ((i % room.width) == (room.width - 1) && rns.get(1) != null) {
                t = exitGen(currentHeight, midHeight, DirectionalVector.EAST) ;

            } else if (i > (room.height - 1) * room.width && rns.get(2) != null) {
                t = exitGen(currentWidth, midWidth, DirectionalVector.SOUTH) ;

            } else if ((i % room.width) == 0 && rns.get(3) != null) {
                t = exitGen(currentHeight, midHeight, DirectionalVector.WEST) ;
                
            } else {
                Occupant o = null;
                if (chance < obstacleChance) {
                    if (RAND.nextBoolean()) {
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

    /**
     * Generates a random enemy, with a random name, max HP between 1 and 100, and attack and defense between 1 and 50.
     * The names are from the list: Zombie, Vampire, Werewolf, Ghost, Goblin, Orc, Troll, Skeleton, Mummy, Golem, Lich, Demon, Dragon, Wraith, Kraken, Hydra, Griffin, Minotaur, Chimera, Harpy
     * @return a new Enemy object
     */
    private static Occupant generateEnemy() {
        List<String> names = Arrays.asList("Zombie", "Vampire", "Werewolf", "Ghost", "Goblin",
                                                "Orc", "Troll", "Skeleton", "Mummy", "Golem",
                                                "Lich", "Demon", "Dragon", "Wraith", "Kraken",
                                                "Hydra", "Griffin", "Minotaur", "Chimera", "Harpy");
        String name = names.get(RAND.nextInt(names.size()));
        return new Enemy(name, RAND.nextInt(101), RAND.nextInt(50), RAND.nextInt(50));
    }

/**
 * Validates if all exit tiles in the given {@link Room} are reachable from one another.
 * 
 * This method checks if there is a path between each pair of exit tiles in the room.
 * It performs a breadth-first search (BFS) from one exit to another and ensures that
 * each exit can be reached from every other exit.
 * 
 * @param room the {@link Room} to validate
 * @return true if all exit tiles are reachable from one another, false otherwise
 */

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