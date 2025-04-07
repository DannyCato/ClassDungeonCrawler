package edu.rit.swen262.service;

/**
 * defines the interface for the GameObserver, which can observe 
 * any number of {@link IObservable IObservables} and be updated whenever
 * they are notified
 */
public interface GameObserver {
    /**
     * updates the observer and performs operations when the state of the
     * {@link GameState GameState object} being observed sends a push notification 
     * @param g the updated version of the GameState object
     */
    public void update(GameEvent event);
}
