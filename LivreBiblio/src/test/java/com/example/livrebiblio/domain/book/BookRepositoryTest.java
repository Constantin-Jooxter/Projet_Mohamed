package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.Author;
import com.example.livrebiblio.domain.author.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void given_books_when_FindAll_then_return_AllBooks() {
        // Arrange
        Author author1 = new Author();
        author1.setName("Auteur du book 1");
        author1.setBirthday(new Date());
        author1.setSurname("Anto");
        authorRepository.save(author1);

        Book book1 = new Book();
        book1.setIsbn("1234567890");
        book1.setTitre("Titre du book 1");
        book1.setAuthor(author1);
        book1.setDatePublication(Instant.now());
        book1.setSynopsis("TestSynopsis 1");
        bookRepository.save(book1);

        Author author2 = new Author();
        author2.setName("Auteur du book 2");
        author2.setBirthday(new Date());
        author2.setSurname("Anto");
        authorRepository.save(author2);

        Book book2 = new Book();
        book2.setIsbn("0987654321");
        book2.setTitre("Titre du book 2");
        book2.setAuthor(author2);
        book2.setDatePublication(Instant.now());
        book2.setSynopsis("TestSynopsis 2");
        bookRepository.save(book2);

        // Act
        List<Book> foundBooks = bookRepository.findAll();

        // Assert
        assertEquals(2, foundBooks.size());
    }

    @AfterEach

    public void Down() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }
}
