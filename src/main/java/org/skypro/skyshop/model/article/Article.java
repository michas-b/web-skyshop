package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleName;
    private final String articleText;
    private final UUID id;

    public Article(String articleName, String articleText, UUID id) {
        if ((articleName == null || articleName.isBlank()) || (articleText == null || articleText.isBlank())) {
            throw new IllegalArgumentException("Артикул должен иметь название и текст");
        }
        if (id == null) {
            throw new IllegalArgumentException("Артикул должен иметь id");
        } else {
            this.articleName = articleName;
            this.articleText = articleText;
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return articleName + "\n" + articleText;
    }

    @Override
    public String getNameSearchable() {
        if (this.articleName == null || this.articleName.isBlank()) {
            return null;
        } else {
            return articleName;
        }
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return this.articleName;
    }

    @JsonIgnore
    @Override
    public String getTypeSearchable() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName);
    }

    //---
}
