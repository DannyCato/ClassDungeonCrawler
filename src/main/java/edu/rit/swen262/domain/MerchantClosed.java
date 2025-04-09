package edu.rit.swen262.domain;

public class MerchantClosed implements MerchantForm {

    public MerchantClosed () {}

    /**
     * @return      Is Null since the shop is closed.
     */
    @Override
    public String handlePurchaseItem(int index, PlayerCharacter player, Merchant merchant) {
        return null;
    }

    /**
     * @return      Is Null since the shop is closed.
     */
    @Override
    public String handleItemSale(Item item, PlayerCharacter player) {
        return null;
    }
}
