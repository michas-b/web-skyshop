package org.skypro.skyshop.model.search;

import java.util.*;
import java.util.stream.Collectors;


public class SearchEngine implements Searchable {
    private Set<Searchable> setSearchable = new HashSet<>();

    public SearchEngine() {
        this.setSearchable = new HashSet<>();
    }

    public boolean addSearchable(Searchable searchableItem) {
        boolean result = false;
        try {
            if (!setSearchable.contains(searchableItem)) {
                this.setSearchable.add(searchableItem);
                result = true;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public TreeSet<Searchable> search(String query) {
        Comparator<Searchable> comparator = (s1, s2) -> {
            int searchableLength = Integer.compare(s2.getNameSearchable().length(), s1.getNameSearchable().length());
            if (searchableLength == 0) {
                return s1.getNameSearchable().compareTo(s2.getNameSearchable());
            }
            else {
                return searchableLength;
            }
        };
        if (this.setSearchable == null || query == null) {
            return new TreeSet<>(comparator);
        }
        return this.setSearchable.stream()
                .filter(item -> item.getNameSearchable().equals(query))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public Searchable bestSearchable(String search) throws BestResultNotFound {
        int count;
        int index;
        int countBestResult = 0;
        Searchable bestResult = null;

        for (Searchable searchableItem : this.setSearchable) {
            count = 0;
            index = 0;
            int indexSubString = searchableItem.getSearchTerm().indexOf(search, index);
            while (indexSubString != -1) {
                count++;
                index = indexSubString + search.length();
                indexSubString = searchableItem.getSearchTerm().indexOf(search, index);
            }
            if (count > countBestResult) {
                bestResult = searchableItem;
                countBestResult++;
            }
        }
        if (bestResult == null) {
            throw new BestResultNotFound("По запросу [" + search + "] не найдено совпадений");
        } else {
            return bestResult;
        }
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getSearchTerm() {
        return "";
    }

    @Override
    public String getTypeSearchable() {
        return "";
    }

    @Override
    public String getNameSearchable() {
        return "";
    }

    @Override
    public String getStringRepresentation() {
        return Searchable.super.getStringRepresentation();
    }


}