package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String productName;
    private final UUID id;

    public Product(String productName, UUID id) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        if (id == null) {
            throw new IllegalArgumentException("Продукт должен иметь id");
        } else {
            this.productName = productName;
            this.id = id;
        }
    }

    public String getProductName() {
        if (this.productName == null || this.productName.isBlank()) {
            return null;
        } else {
            return productName;
        }
    }

    public abstract int getProductPrice();

    public abstract boolean isSpecial();

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public String getNameSearchable() {
        return this.getProductName();
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return this.getProductName();
    }

    @Override
    @JsonIgnore
    public String getTypeSearchable() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

}
