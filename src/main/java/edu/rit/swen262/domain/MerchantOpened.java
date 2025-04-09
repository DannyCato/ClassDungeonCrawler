package edu.rit.swen262.domain;

import java.util.List;

/**
 * concrete state of the {@link Merchant} wwhich represents its form when
 * open and interactable in "safe" conditions
 * 
 * @author Ryan M.
 */
public class MerchantOpened implements MerchantForm {

    public MerchantOpened() {}

    /**
     * Gets the items from the passed in Merchant, uses the index to find the item wanted and gives the player the item
     * if they have the inventory space or enough gold to buy the item.
     * 
     * @param index         Index of the item wanting to buy from the list of items being sold.
     * @param player        The current player trying to buy from the shop.
     * @return              The String result stating what happened
     */
    @Override
    public String handlePurchaseItem(int index, PlayerCharacter player, Merchant merchant) {
        List<Item> shop = merchant.getShopItems();
        Item wanted = shop.get(index);
        Gold cost = wanted.getValue();
        Gold budget = player.gold;

        if (cost.getCount() <= budget.getCount()) {
            String result = player.addItemToBag(wanted);
            
            if (result != "Inventory full!") {
                budget.setCount(budget.getCount() - cost.getCount());
            }

            return result;
        }
        else {
            return "Not enough Gold.";
        }
    }

    /**
     * Requires the item the player wants to sell, finds the item in the players inventory, 
     * removes the item form the player, than gives them half the gold the items worth.
     * 
     * @param item          The item that player wants to sell.
     * @param player        The current player trying to buy from the shop.
     * @return              The String result stating what happened
     */
    @Override
    public String handleItemSale(Item item, PlayerCharacter player) {
        Item itemToSell = player.findItem(item);

        player.dropItem(itemToSell);

        Gold cost = itemToSell.getValue();
        Gold wallet = player.gold;
        int returnGold = Math.round(cost.getCount()/2);

        wallet.setCount(wallet.getCount() + returnGold);

        return "You received " + returnGold + " Gold for selling " + itemToSell.getName() + ".";
    }
}
