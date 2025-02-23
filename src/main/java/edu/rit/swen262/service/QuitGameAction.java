package edu.rit.swen262.service;

/**
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class QuitGameAction implements Action {
    private GameState gameState;

    public QuitGameAction(GameState gameState) {
        this.gameState = gameState;
    }

    /*
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.quit();
    }
}
