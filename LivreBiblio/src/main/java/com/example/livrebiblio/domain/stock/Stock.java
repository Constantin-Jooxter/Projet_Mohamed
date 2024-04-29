package com.example.livrebiblio.domain.stock;


import com.example.livrebiblio.domain.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
