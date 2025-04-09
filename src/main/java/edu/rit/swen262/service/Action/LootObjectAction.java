package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.Lootable;
import edu.rit.swen262.domain.PlayerCharacter;
import edu.rit.swen262.service.GameState;

public class LootObjectAction implements Action {
    private GameState gameState;
    private PlayerCharacter player;
    private Lootable lootable;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param lootable the object to be looted
     */
    public LootObjectAction(GameState gameState, PlayerCharacter player, Lootable lootable) {
        this.gameState = gameState;
        this.lootable = lootable;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.lootObject(player, lootable);;
    }

    /**
     * returns the toString
     */
    @Override
    public String toString() {
        return "Loot all from " + this.lootable.getClass().getSimpleName();
    }
}
