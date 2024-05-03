package com.example.livrebiblio.domain.review;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_books_id")
    private Book book;

    private Integer rating;
    private String comment;
}
