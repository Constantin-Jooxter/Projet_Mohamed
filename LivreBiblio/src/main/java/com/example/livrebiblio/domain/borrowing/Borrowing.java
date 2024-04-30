package com.example.livrebiblio.domain.borrowing;


import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_books_id")
    private Book book;

    private LocalDate borrowingdate;
    private LocalDate returndate;
}
