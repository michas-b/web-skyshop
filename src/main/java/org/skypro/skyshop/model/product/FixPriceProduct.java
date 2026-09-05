package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE_PRODUCT = 35;
    private final UUID id;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getProductPrice() {
        return FIXED_PRICE_PRODUCT;
    }

    @Override
    public String toString() {
        return super.getProductName() + ": Фиксированная цена " + this.getProductPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
