package edu.rit.swen262.service.Action;

import edu.rit.swen262.domain.Item;
import edu.rit.swen262.domain.Merchant;
import edu.rit.swen262.service.GameState;

public class BuyItemAction implements Action{
    private GameState gameState;
    private Merchant merchant;
    private Item item;
    private int itemIndex;

    /**
     * instantiates a {@link Action concrete command} with the given {@link GameState}
     * 
     * @param gameState object containing the current state of the game
     * @param item the item to be used by the player
     */
    public BuyItemAction(GameState gameState, Merchant merchant, Item item, int itemIndex) {
        this.gameState = gameState;
        this.merchant = merchant;
        this.item = item;
        this.itemIndex = itemIndex;
    }

    /**
     * {@inheritDoc}
     */
    public void performAction() {
        gameState.buyItem(this.merchant, this.item, this.itemIndex);
    }

    /**
     * returns the toString
     */
    @Override
    public String toString() {
        String itemClassText = this.item.getClass().getSimpleName();
        return this.item.getName() + " (" + itemClassText + " " + this.item.getValue().getCount() + "G)";
    }
}
