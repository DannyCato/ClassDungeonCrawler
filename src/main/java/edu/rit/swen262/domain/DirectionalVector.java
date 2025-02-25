package edu.rit.swen262.domain;

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

    private DirectionalVector(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
