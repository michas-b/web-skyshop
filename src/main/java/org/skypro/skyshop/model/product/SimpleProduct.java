package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int productPrice;
    private final UUID id;

    public SimpleProduct(String productName, int productPrice, UUID id) {
        super(productName, id);
        this.id = id;
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Стоимость должна быть больше 0");
        } else {
            this.productPrice = productPrice;
        }
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getProductPrice() {
        return this.productPrice;
    }

    @Override
    public String toString() {
        return this.getProductName() + ": " + this.getProductPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
    ///
}
