package edu.rit.swen262.service;

/**
 * defines the interface for the GameObserver, which can observe 
 * any number of {@link IObservable IObservables} and be updated whenever
 * they are notified
 */
public interface GameObserver {
    /**
     * updates the field storing the latest version of the GameState
     * @param g the updated version of the GameState object
     */
    public void update(GameEvent event);
}
