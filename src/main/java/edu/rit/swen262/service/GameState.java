package edu.rit.swen262.service;

import java.util.List;

import edu.rit.swen262.domain.PlayerCharacter;

import java.util.ArrayList;

public class GameState implements IObservable {
    private List<GameObserver> observers;
    private PlayerCharacter player;

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

    public List<GameObserver> getObservers() {
        return observers;
    }
}
