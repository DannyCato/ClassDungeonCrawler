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

    private String displayName;

    public static ArrayList<DirectionalVector> directions = new ArrayList<>(Arrays.asList(DirectionalVector.values()));

    private DirectionalVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the opposite {@link DirectionalVector} of a given {@link DirectionalVector}
     * 
     * @param dir {@link DirectionalVector}
     * @return {@link DirectionalVector} the opposite of dir
     */
    public static DirectionalVector getOppositeDirection(DirectionalVector dir) {
        return directions.get((directions.indexOf(dir) + 4) % 8);
    }

    /**
     * Returns whether two given {@link DirectionalVector DirectionalVectors} are opposite from one another
     * 
     * @param d1 {@link DirectionalVector}
     * @param d2 {@link DirectionalVector}
     * @return a boolean of whether they were opposites or not
     */
    public static boolean directionsAreOpposite(DirectionalVector d1, DirectionalVector d2) {
        return (Math.abs(directions.indexOf(d1) - directions.indexOf(d2)) == 4);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return displayName;
    }
}
