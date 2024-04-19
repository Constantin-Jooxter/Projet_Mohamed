package com.example.livrebiblio.domain.livre;

import java.time.Instant;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecificationBuilder {


    private Specification<Book> instance;

    private BookSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static BookSpecificationBuilder builder() {
        return new BookSpecificationBuilder();
    }

    
    public BookSpecificationBuilder withIsbn(@NonNull String isbn) {
        instance = instance.and((root, query, builder) ->
                builder.equal(root.get("isbn"), isbn));
        return this;
    }

    public BookSpecificationBuilder withAuteur(@NonNull String auteur) {
        instance = instance.and((root, query, builder) ->
                builder.equal(root.get("auteur"), auteur));
        return this;
    }
    public BookSpecificationBuilder withTitre(@NonNull String titre) {
        instance = instance.and((root, query, builder) ->
                builder.equal(root.get("titre"), titre));
        return this;
    }
    public BookSpecificationBuilder withSynopsis(@NonNull String synopsis) {
        instance = instance.and((root, query, builder) ->
                builder.equal(root.get("synopsis"), synopsis));
        return this;
    }
    public BookSpecificationBuilder withDatePublication(@NonNull Instant datePublication) {
        instance = instance.and((root, query, builder) ->
                builder.equal(root.get("datePublication"), datePublication));
        return this;
    }

    public Specification<Book> build() {
        return this.instance;
    }
}



