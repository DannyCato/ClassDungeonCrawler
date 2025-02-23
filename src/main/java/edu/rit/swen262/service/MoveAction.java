package edu.rit.swen262.service;

/**
 * 
 * 
 * @auhor Victor Bovat, Philip Rubbo
 */
public class MoveAction implements Action {
    private GameState gameState;
    private String direction;

    public MoveAction(GameState gameState, String direction) {
        this.gameState = gameState;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        // gameState.movePlayer(this.direction) ?
    }
}


