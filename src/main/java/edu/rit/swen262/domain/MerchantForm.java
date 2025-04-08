package edu.rit.swen262.domain;

public interface MerchantForm {
    String handlePurchaseItem(int index, PlayerCharacter player);
    String handleItemSale(Item item, PlayerCharacter player);
}
