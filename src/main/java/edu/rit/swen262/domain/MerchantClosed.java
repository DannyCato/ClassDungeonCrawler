package edu.rit.swen262.domain;

/**
 * concrete state of the {@link Merchant} wwhich represents its form when
 * closed and non-interatable due to "unsafe conditions": {@link NightTime}
 * and/or the presence of an enemy
 * 
 * @author Ryan M.
 */
public class MerchantClosed implements MerchantForm {

    public MerchantClosed () {}

    /**
     * handles purchasing item from closed merchant
     * 
     * @return {@code null} since the shop is closed.
     */
    @Override
    public String handlePurchaseItem(int index, PlayerCharacter player, Merchant merchant) {
        return null;
    }

    /**
     * handles item selling to closed merchant
     * 
     * @return {@code null} since the shop is closed.
     */
    @Override
    public String handleItemSale(Item item, PlayerCharacter player) {
        return null;
    }
}
