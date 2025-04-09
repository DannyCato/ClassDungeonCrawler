package edu.rit.swen262.domain;

/**
 * interface which defines the form-dependent behavior for a 
 * {@link Merchant} of buying and selling items based upon {@link Map} 
 * and {@link GameState} conditions
 * 
 * @author Ryan M.
 */
public interface MerchantForm {
    String handlePurchaseItem(int index, PlayerCharacter player, Merchant merchant);
    String handleItemSale(Item item, PlayerCharacter player);
}
