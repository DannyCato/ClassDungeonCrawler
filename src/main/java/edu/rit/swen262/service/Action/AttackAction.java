package edu.rit.swen262.service.Action;

import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for initiating a single
 * directional attack by the {@link PlayerCharacter} on the map in the MUD game
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class AttackAction implements Action {
    private GameState gameState;
    private String direction;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param direction the direction in which to move the Player
     */
    public AttackAction(GameState gameState, String direction) {
        this.gameState = gameState;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.movePlayer(this.direction);
    }

    @Override
    public String toString() {
        return this.direction;
    }
}


