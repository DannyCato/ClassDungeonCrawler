package edu.rit.swen262.service;

/**
 * represents a single {@link Action} which is responsible for moving
 * the PlayerCharacter on the map in the MUD game
 * 
 * @auhor Victor Bovat, Philip Rubbo
 */
public class MoveAction implements Action {
    private GameState gameState;
    private String direction;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param direction the direction in which to move the Player
     */
    public MoveAction(GameState gameState, String direction) {
        this.gameState = gameState;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.movePlayer(this.direction);
    }
}


