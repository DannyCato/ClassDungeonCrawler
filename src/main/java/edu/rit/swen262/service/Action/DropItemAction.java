package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.Item;
import edu.rit.swen262.service.GameState;

/**
 * represents a single {@link Action} which is responsible for activating the effects
 * of an {@link Item} upon the {@link PlayerCharacter}
 * 
 * @author Victor Bovat, Philip Rubbo
 */
public class DropItemAction implements Action {
    private GameState gameState;
    private Item item;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param item the item to be dropped by the player
     */
    public DropItemAction(GameState gameState, Item item) {
        this.gameState = gameState;
        this.item = item;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.dropItem(this.item);
    }

    /**
     * returns the toString
     */
    @Override
    public String toString() {
        return this.item.toString();
    }
}
