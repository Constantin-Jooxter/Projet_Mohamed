package com.example.livrebiblio.domain.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void given_books_when_FindAll_then_return_AllBooks() {
        // Given
        Book book1 = new Book();
        book1.setIsbn("1234567890");
        book1.setTitre("Titre du book 1");
        book1.setAuthor("Auteur du book 1");
        book1.setDatePublication(Instant.now());
        book1.setSynopsis("TestSynopsis 1");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setIsbn("0987654321");
        book2.setTitre("Titre du book 2");
        book2.setAuthor("Auteur du book 2");
        book2.setDatePublication(Instant.now());
        book2.setSynopsis("TestSynopsis 2");
        bookRepository.save(book2);

        // When
        List<Book> foundBooks = bookRepository.findAll();

        // Then
        Assertions.assertEquals(2, foundBooks.size());
    }

    @AfterEach
    public void Down() {
        bookRepository.deleteAll();
    }
}
