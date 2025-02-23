package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.PlayerCharacter;

import java.util.ArrayList;

/**
 * The class which represents the current state of the game being played.
 * Both receives and manages commands as well as updates all interested observers
 * whenever a change in the game's state has occurred.
 * 
 * @author Victor Bovat
 */
public class GameState implements IObservable {
    private List<GameObserver> observers;
    private PlayerCharacter player;
    private int turnNumber;

    /**
     * creates a new GameState object with the specific player character, initializing
     * the list of active observers to be empty by default
     * 
     * @param player the player starting the new game
     */
    public GameState(PlayerCharacter player) {
        this.observers = new ArrayList<>();
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    public void register(GameObserver go) {
        observers.add(go);
    }

    /**
     * {@inheritDoc}
     */
    public void deregister(GameObserver go) {
        observers.remove(go);
    }

    /**
     * {@inheritDoc}
     */
    public void notifyObservers() {
        for (GameObserver o : observers) {
            o.update(this);
        }
    }

    /**
     * fetches the list of {@link GameObserver GameObservers} currently watching
     * this GameState for any new changes
     * 
     * @return a list of all active {@link GameObserver GameObservers}
     */
    public List<GameObserver> getObservers() {
        return observers;
    }
}
