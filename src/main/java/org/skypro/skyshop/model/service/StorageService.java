package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;


    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();

        initTestData();
    }

    private void initTestData() {
        SimpleProduct battery = new SimpleProduct("батарейка", 100, UUID.randomUUID());
        productMap.put(battery.getId(), battery);
        SimpleProduct toy = new SimpleProduct("игрушка", 300, UUID.randomUUID());
        productMap.put(toy.getId(), toy);
        FixPriceProduct iceCream = new FixPriceProduct("мороженое", UUID.randomUUID());
        productMap.put(iceCream.getId(), iceCream);
        SimpleProduct phone = new SimpleProduct("телефон", 1000, UUID.randomUUID());
        productMap.put(phone.getId(), phone);
        SimpleProduct mouse = new SimpleProduct("мышь", 200, UUID.randomUUID());
        productMap.put(mouse.getId(), mouse);
        DiscountedProduct car = new DiscountedProduct("автомобиль", 2000000, 15, UUID.randomUUID());
        productMap.put(car.getId(), car);
        Article batteryArticle = new Article("battery", "батарея", UUID.randomUUID());
        articleMap.put(batteryArticle.getId(), batteryArticle);
        Article toyArticle = new Article("toy", "игрушка", UUID.randomUUID());
        articleMap.put(toyArticle.getId(), toyArticle);
    }

    public Collection<Product> getProducts() {
        return productMap.values();
    }

    public Collection<Article> getArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getSearchables() {
        return Stream.concat(this.productMap.values().stream(), this.articleMap.values().stream()).toList();
    }
}
