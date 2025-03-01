package edu.rit.swen262.service.Action;

import edu.rit.swen262.service.GameState;

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

    /**
     * fetches the {@link GameState} that the action is bound to
     * 
     * @return the {@link GameState} object executing the command
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * fetches the Direction String that the action is bound to
     * 
     * @return the Direction String executing the command
     */
    public String getDirection() {
        return this.direction;
    }

    @Override
    public String toString() {
        return this.direction;
    }
}


