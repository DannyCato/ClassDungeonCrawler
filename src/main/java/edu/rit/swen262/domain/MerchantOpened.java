package edu.rit.swen262.domain;

import java.util.List;

public class MerchantOpened implements MerchantForm {
    private Merchant merchant;

    public MerchantOpened(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public String handlePurchaseItem(int index, PlayerCharacter player) {
        List<Item> shop = merchant.getShopItems();
        Item wanted = shop.get(index);
        Gold cost = wanted.getValue();
        Gold budget = player.gold;

        if (cost.getCount() <= budget.getCount()) {
            String result = player.addItemToBag(wanted);
            
            if (!(result == "Inventory full!")) {
                budget.setCount(budget.getCount() - cost.getCount());
            }

            return result;
        }
        else {
            return "Not enough Gold.";
        }
    }

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
