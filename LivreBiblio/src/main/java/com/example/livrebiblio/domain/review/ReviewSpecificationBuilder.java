package com.example.livrebiblio.domain.review;


import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.borrowing.Borrowing;
import com.example.livrebiblio.domain.users.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecificationBuilder {

    private Specification<Reviews> instance;

    private ReviewSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static ReviewSpecificationBuilder builder() {
        return new ReviewSpecificationBuilder();
    }

    public ReviewSpecificationBuilder withRating(Integer rating) {
        if (rating != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("rating"), rating));
        }
        return this;
    }

    public ReviewSpecificationBuilder withUserName(String name) {
        if (name != null) {
            instance = instance.and((root, query, builder) -> {
                Join<Borrowing, User> UserJoin = root.join("user", JoinType.INNER);
                return builder.equal(UserJoin.get("name"), name);
            });
        }
        return this;
    }

    public ReviewSpecificationBuilder withBookName(String title) {
        if (title != null) {
            instance = instance.and((root, query, builder) -> {
                Join<Borrowing, Book> bookJoin = root.join("book", JoinType.INNER);
                return builder.equal(bookJoin.get("title"), title);
            });
        }
        return this;
    }

    public Specification<Reviews> build() {
        return this.instance;
    }
}
