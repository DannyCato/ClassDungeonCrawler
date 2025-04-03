package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.DirectionalVector;
import edu.rit.swen262.domain.Inventory;
import edu.rit.swen262.service.GameState;
import edu.rit.swen262.ui.MUDGameUI;

/**
 * represents a single {@link Action} which is responsible for opening the 
 * {@link Inventory} of the {@link PLayerCharacter player} and update the display 
 * within the {@link MUDGameUI UI}
 * 
 * @author Victor Bovat
 */
public class OpenInventoryAction implements Action {
    private GameState gameState;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     */
    public OpenInventoryAction(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.openInventory();
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
