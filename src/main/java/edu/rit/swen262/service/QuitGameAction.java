package edu.rit.swen262.service;

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
        System.out.println("quit quit quit");
        gameState.quit();
    }
}
