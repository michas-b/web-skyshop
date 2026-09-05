package org.skypro.skyshop.model.basket;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> productBasket = new HashMap<>();

    public ProductBasket() {

    }

    public void addProduct(UUID id) {
        if(id != null) {
            this.productBasket.merge(id, 1, Integer::sum);
        }
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(productBasket);
    }
}