package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int productBasePrice;
    private int discountPercent;
    private final UUID id;

    public DiscountedProduct(String productName, int productPrice, int discountPercent, UUID id) {
        super(productName, id);
        this.id = id;
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Базовая цена не может быть равной или меньше 0");
        } else if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапозоне от 0% до 100%");
        } else {
            this.productBasePrice = productPrice;
            this.discountPercent = discountPercent;
        }
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getProductPrice() {
        return (this.productBasePrice - ((this.productBasePrice / 100) * this.discountPercent));
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String toString() {
        return this.getProductName() + ": " + this.getProductPrice() + " (" + this.getDiscountPercent() + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    ///
}
