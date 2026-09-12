package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class StorageServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private StorageService storageService;

    @Test
    void testSearchNullResult() {
        Mockito.when(storageService.getSearchables()).thenReturn(Collections.emptyList());
        Collection<SearchResult> result = searchService.search("какой-то запрос");
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(storageService).getSearchables();
    }

    @Test
    void testNotSearchResult() {
        Mockito.when(storageService.getSearchables()).thenReturn(List.of(new SimpleProduct("Груша", 1000, UUID.randomUUID())));
        Collection<SearchResult> result = searchService.search("Яблоко");
        Assertions.assertTrue(result.isEmpty());
        Mockito.verify(storageService).getSearchables();
    }

    @Test
    void testGoodSearchResult() {
        Mockito.when(storageService.getSearchables()).thenReturn(List.of(new SimpleProduct("Яблоко", 900, UUID.randomUUID())));
        Collection<SearchResult> result = searchService.search("Яблоко");
        Assertions.assertFalse(result.isEmpty());
        Mockito.verify(storageService).getSearchables();
    }

}
