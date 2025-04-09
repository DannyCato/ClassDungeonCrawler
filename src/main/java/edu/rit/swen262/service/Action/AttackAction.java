package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for initiating a single
 * directional attack by the {@link PlayerCharacter} on the map in the MUD game
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class AttackAction implements Action {
    private GameState gameState;
    private DirectionalVector direction;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param direction the direction in which to attempt an attack
     */
    public AttackAction(GameState gameState, DirectionalVector direction) {
        this.gameState = gameState;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.attackCharacter(direction);
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
     * fetches the direction to attempt an attack in
     * 
     * @return the direction vector for the attack
     */
    public DirectionalVector getDirection() {
        return this.direction;
    }

    /*
     * returns the toString
     */
    @Override
    public String toString() {
        return this.direction.toString();
    }
}


