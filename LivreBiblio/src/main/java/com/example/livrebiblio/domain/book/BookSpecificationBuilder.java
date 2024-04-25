package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.Author;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class BookSpecificationBuilder {

    private Specification<Book> instance;

    private BookSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static BookSpecificationBuilder builder() {
        return new BookSpecificationBuilder();
    }


    public BookSpecificationBuilder withIsbn(String isbn) {
        if (isbn != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("isbn"), isbn));
        }
        return this;
    }

    public BookSpecificationBuilder withAuteurName(String authorName) {
        if (authorName != null) {
            instance = instance.and((root, query, builder) -> {
                Join<Book, Author> authorJoin = root.join("author", JoinType.INNER);
                return builder.equal(authorJoin.get("name"), authorName);
            });
        }
        return this;
    }


    public BookSpecificationBuilder withTitre(String titre) {
        if (titre != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("titre"), titre));
        }
        return this;
    }

    public BookSpecificationBuilder withSynopsis(String synopsis) {
        if (synopsis != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("synopsis"), synopsis));
        }
        return this;
    }

    public BookSpecificationBuilder withdatePublication(Instant datePublication) {
        if (datePublication != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("datePublication"), datePublication));
        }
        return this;
    }

    public Specification<Book> build() {
        return this.instance;
    }
}



