package edu.rit.swen262.service;

public interface GameObserver {
    /**
     * updates the field storing the latest version of the GameState
     * @param g the updated version of the GameState object
     */
    public void update(GameState g);
}
