package edu.rit.swen262.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Position Vector to help Room move objects
 * 
 * @author Danny Catorcini
 */
public enum DirectionalVector {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1);

    public int x;

    public int y;

    public static ArrayList<DirectionalVector> directions = new ArrayList<>(Arrays.asList(DirectionalVector.values()));

    private DirectionalVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static DirectionalVector getOppositeDirection(DirectionalVector dir) {
        return directions.get((directions.indexOf(dir) + 4) % 8);
    }
}
