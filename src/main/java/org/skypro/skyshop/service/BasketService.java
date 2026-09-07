package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {
    private final StorageService storageService;
    private final ProductBasket productBasket;

    @Autowired
    public BasketService(StorageService storageService, ProductBasket productBasket) {
        this.storageService = storageService;
        this.productBasket = productBasket;
    }

    public void addProduct(UUID id) throws IllegalArgumentException {
        if (storageService.getProductById(id).isPresent()) {
            productBasket.addProduct(id);
        } else {
            throw new IllegalArgumentException("Продукта с id: " + id.toString() + " не существует");
        }
    }

    public UserBasket getUserBasket() {
        return new UserBasket(this.productBasket.getProductBasket().entrySet().stream()
                .map((entry) -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(), entry.getValue()))
                .toList());
    }
}
