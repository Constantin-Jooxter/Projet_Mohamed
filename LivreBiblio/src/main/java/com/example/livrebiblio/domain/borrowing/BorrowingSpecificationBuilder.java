package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.users.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BorrowingSpecificationBuilder {

    private Specification<Borrowing> instance;

    private BorrowingSpecificationBuilder() {
        this.instance = Specification.where(null);
    }

    public static BorrowingSpecificationBuilder builder() {
        return new BorrowingSpecificationBuilder();
    }

    public BorrowingSpecificationBuilder withBorrowingDate(LocalDate start_date) {
        if (start_date != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("start_date"), start_date));
        }
        return this;
    }

    public BorrowingSpecificationBuilder withReturnDate(LocalDate end_date) {
        if (end_date != null) {
            instance = instance.and((root, query, builder) ->
                    builder.equal(root.get("end_date"), end_date));
        }
        return this;
    }

    public BorrowingSpecificationBuilder withUserName(String UserName) {
        if (UserName != null) {
            instance = instance.and((root, query, builder) -> {
                Join<Borrowing, User> UserJoin = root.join("user", JoinType.INNER);
                return builder.equal(UserJoin.get("name"), UserName);
            });
        }
        return this;
    }

    public BorrowingSpecificationBuilder withBookName(String title) {
        if (title != null) {
            instance = instance.and((root, query, builder) -> {
                Join<Borrowing, Book> bookJoin = root.join("book", JoinType.INNER);
                return builder.equal(bookJoin.get("title"), title);
            });
        }
        return this;
    }

    public Specification<Borrowing> build() {
        return this.instance;
    }
}
