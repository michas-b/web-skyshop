package org.skypro.skyshop.service;


import org.springframework.stereotype.Service;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;


@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchTerm) {
        return storageService.getSearchables().stream()
                .filter(item -> item.getNameSearchable().toLowerCase().contains(searchTerm.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}