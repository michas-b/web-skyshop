package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> itemsBasket;
    private final int total;

    public UserBasket(List<BasketItem> itemsList) {
        this.itemsBasket = itemsList;
        this.total = itemsBasket.stream().mapToInt(item ->
                item.getProduct().getProductPrice() * item.getQuantity()).sum();
    }

    public int getTotalPrice() {
        return this.total;
    }
}
