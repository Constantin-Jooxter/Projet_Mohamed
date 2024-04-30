package com.example.livrebiblio.domain.users;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@AllArgsConstructor
public class UserSpecificationBuilder {
    private Specification<User> instance;

    private UserSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static UserSpecificationBuilder builder() {
        return new UserSpecificationBuilder();
    }

    public UserSpecificationBuilder withName(String name) {
        if (name != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("name"), name));
        }
        return this;
    }

    public UserSpecificationBuilder withSurname(String surname) {
        if (surname != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("surname"), surname));
        }
        return this;
    }

    public UserSpecificationBuilder withRegistration(LocalDate registration) {
        if (registration != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("registration"), registration));
        }
        return this;
    }

    public UserSpecificationBuilder withBorrowing(LocalDate borrowing) {
        if (borrowing != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("borrowing"), borrowing));
        }
        return this;
    }

    public Specification<User> build() {
        return this.instance;
    }

}
