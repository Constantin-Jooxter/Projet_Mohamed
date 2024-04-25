package com.example.livrebiblio.domain.author;


import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AuthorSpecificationBuilder {

    private Specification<Author> instance;

    private AuthorSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static AuthorSpecificationBuilder builder() {
        return new AuthorSpecificationBuilder();
    }

    public AuthorSpecificationBuilder withName(String name) {
        if (name != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("name"), name));
        }
        return this;
    }

    public AuthorSpecificationBuilder withSurname(String surname) {
        if (surname != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("surname"), surname));
        }
        return this;
    }

    public AuthorSpecificationBuilder withBirthday(Date birthday) {
        if (birthday != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("birthday"), birthday));
        }
        return this;
    }

    public Specification<Author> build() {
        return this.instance;
    }
}
