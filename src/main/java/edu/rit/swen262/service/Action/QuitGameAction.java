package edu.rit.swen262.service.Action;

import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for exiting
 * the MUD game
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class QuitGameAction implements Action {
    private GameState gameState;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     */
    public QuitGameAction(GameState gameState) {
        this.gameState = gameState;
    }

    /*
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.quit();
    }

    /**
     * fetches the {@link GameState} that the action is bound to
     * 
     * @return the {@link GameState} object executing the command
     */
    public GameState getGameState() {
        return this.gameState;
    }
}
