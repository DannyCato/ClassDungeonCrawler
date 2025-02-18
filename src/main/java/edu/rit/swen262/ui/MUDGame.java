package edu.rit.swen262.ui;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameObserver;
import edu.rit.swen262.service.GameState;

public class MUDGame implements GameObserver {
    private GameState gameState;

    /**
     * {@inheritDoc}
     */
    public void update(GameState g) {
        this.gameState = g;
        System.out.println("client notified! game state has been updated");
    }

    public void start() {
        PlayerCharacter p = new PlayerCharacter("Bobert", "incredibly underprepared for this dungeon life");
        GameState g = new GameState(p);
        this.gameState = g;

        System.out.println("game initialized!");
    }
}
