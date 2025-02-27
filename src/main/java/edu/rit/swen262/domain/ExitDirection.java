package edu.rit.swen262.domain;

/**
 * Enum to represent the cardinal directions an exit can face on the wall
 * 
 * Represents the direction of the wall the door is on, i.e. north = top wall, west = left wall
 * 
 * @author Eric Manning
 */
public enum ExitDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;
    
    /**
     * Compare two directions to see if they are compatible
     * North and North will not be compatible,
     * only North and South, and East and West
     * 
     * @param thisExit the first exit to compare
     * @param otherExit the second exit to compare
     * @return boolean. true if they are opposite directions, false otherwise
     */
    public boolean isCompatable(ExitDirection thisExit, ExitDirection otherExit) {
        return thisExit.equals(ExitDirection.NORTH) && otherExit.equals(ExitDirection.SOUTH) || thisExit.equals(ExitDirection.SOUTH) && otherExit.equals(ExitDirection.NORTH) || thisExit.equals(ExitDirection.WEST) && otherExit.equals(ExitDirection.EAST) || thisExit.equals(ExitDirection.EAST) && otherExit.equals(ExitDirection.WEST);
    }
}
