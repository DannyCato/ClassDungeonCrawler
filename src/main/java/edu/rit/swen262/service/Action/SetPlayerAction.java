package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for updating the details of
 * the {@link PlayerCharacter} in the {@link GameState}
 * 
 * @author Victor Bovat
 */
public class SetPlayerAction implements Action {
    private GameState gameState;
    private PlayerCharacter player;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     */
    public SetPlayerAction(GameState gameState, PlayerCharacter player) {
        this.gameState = gameState;
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.updatePlayer(this.player);
    }

    /**
     * updates the data of the {@link PlayerCharacter player} this
     * command is bound to
     * 
     * @param player the new player data to update to
     */
    public void setPlayerData(PlayerCharacter player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return this.player.toString();
    }
}
