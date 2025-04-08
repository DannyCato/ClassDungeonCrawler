package edu.rit.swen262.domain;

public class MerchantClosed implements MerchantForm {
    private Merchant merchant;

    public MerchantClosed (Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public void handlePurchaseItem() {
        
    }
}
