package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @InjectMocks
    BasketService basketService;

    @Mock
    ProductBasket productBasket;

    @Mock
    StorageService storageService;

    @Test
    void testAddNotProductToBasket() {
        UUID productId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> this.basketService.addProduct(productId));
        Mockito.verify(productBasket, Mockito.never()).addProduct(Mockito.any());
    }

    @Test
    void testAddProductToBasket() {
        UUID productId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(new SimpleProduct("Груша", 1000, productId)));
        basketService.addProduct(productId);
        Mockito.verify(productBasket, Mockito.times(1)).addProduct(Mockito.eq(productId));
    }

    @Test
    void testGetUserBasketWhenEmpty() {
        Mockito.when(productBasket.getProductBasket()).thenReturn(Collections.emptyMap());
        UserBasket result = basketService.getUserBasket();
        Assertions.assertTrue(result.getTotalPrice()==0);
    }

    @Test
    void testGetUserBasketWhenNotEmpty() {
        UUID productId = UUID.randomUUID();
        Mockito.when(productBasket.getProductBasket()).thenReturn(Map.of(productId, 10));
        Mockito.when(storageService.getProductById(productId)).thenReturn(Optional.of(new SimpleProduct("Груша", 1000, productId)));
        UserBasket result = basketService.getUserBasket();
        Assertions.assertTrue(result.getTotalPrice()>0);
    }

}
