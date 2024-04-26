package com.example.livrebiblio.domain.book;


import com.example.livrebiblio.domain.author.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private Instant datePublication;

    @Column(nullable = false)
    private String synopsis;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public String formatDatePublication() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(datePublication.atZone(ZoneId.systemDefault()));
    }
}