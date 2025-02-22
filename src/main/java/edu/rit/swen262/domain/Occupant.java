package edu.rit.swen262.domain;

/**
 * Defines the interface for Occupants to be on Tiles
 * 
 * @author Danny Catorcini
 */
public interface Occupant {
    /**
     * Give a character to be displayed for CLI
     * 
     * @return a character representing the Occupant
     */
    public RenderRepresentation render();

    /**
     * Return a description of the Occupant to be used for full descriptions of DungeonPieces
     * 
     * @return a description of the Occupant
     */
    public String description();
}
