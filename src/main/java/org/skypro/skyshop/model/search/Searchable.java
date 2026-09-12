package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    UUID getId();

    String getSearchTerm();

    String getTypeSearchable();

    String getNameSearchable();

    default String getStringRepresentation() {
        return this.getNameSearchable() + "-" + this.getTypeSearchable();
    }
}
///
