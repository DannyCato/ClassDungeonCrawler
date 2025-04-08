package edu.rit.swen262.domain;

public class MerchantOpened implements MerchantForm {
    private Merchant merchant;

    public MerchantOpened(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public void handlePurchaseItem() {
        
    }
}
