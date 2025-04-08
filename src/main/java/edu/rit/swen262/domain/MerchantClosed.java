package edu.rit.swen262.domain;

public class MerchantClosed implements MerchantForm {
    private Merchant merchant;

    public MerchantClosed (Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * @return      Is Null since the shop is closed.
     */
    @Override
    public String handlePurchaseItem(int index, PlayerCharacter player) {
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
