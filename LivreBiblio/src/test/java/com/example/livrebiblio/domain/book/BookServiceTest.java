package com.example.livrebiblio.domain.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    // POST


    @Test
    void should_create_book_when_bookRequest_is_given() {
        // Arrange
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", "Auteur du book", "1988", "TestSynopsis");
        Book savedBook = new Book();
        savedBook.setIsbn("1234567890");
        savedBook.setTitre("Titre du book");
        savedBook.setAuteur("Auteur du book");
        savedBook.setDatePublication(null);
        savedBook.setSynopsis("TestSynopsis");

        when(bookRepository.save(savedBook)).thenReturn(savedBook);

        // Act
        BookDTO result = bookService.createBook(bookRequest);

        // Assert
        BookDTO expectedDTO = new BookDTO(savedBook.getIsbn(), savedBook.getTitre(), savedBook.getAuteur(), savedBook.getDatePublication(), savedBook.getSynopsis());
        Assertions.assertThat(result).isEqualTo(expectedDTO);
        verify(bookRepository).save(savedBook);
    }


    // DELETE

    @Test
    void should_delete_book_when_existing_id_given() throws BookNotFoundException {
        // Arrange
        Long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(new Book()));

        // Act
        bookService.deleteBook(bookId);

        // Assert
        verify(bookRepository).deleteById(bookId);
    }

    @Test
    void should_throw_exception_when_non_existing_id_given() {
        // Arrange
        Long nonExistingBookId = 99L;
        when(bookRepository.findById(nonExistingBookId)).thenReturn(Optional.empty());

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(BookNotFoundException.class, () -> {
            bookService.deleteBook(nonExistingBookId);
        });
    }


    // PUT


    @Test
    void should_update_book_when_bookRequest_is_given() throws BookNotFoundException {
        // Arrange
        Long bookId = 6L;
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", "Auteur du book", "1988", "TestSynopsis");
        Book savedBook = new Book();
        savedBook.setId(bookId);
        savedBook.setIsbn("1234567890");
        savedBook.setTitre("Titre du book");
        savedBook.setAuteur("Auteur du book");
        savedBook.setDatePublication(null);
        savedBook.setSynopsis("TestSynopsis");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(savedBook));
        when(bookRepository.save(savedBook)).thenReturn(savedBook);

        // Act
        BookRequest result = bookService.updateBook(bookId, bookRequest);

        // Assert
        verify(bookRepository).findById(bookId);

        verify(bookRepository).save(savedBook);

        Assertions.assertThat(result).isEqualTo(bookRequest);
    }


    @Test
    void should_throw_BookNotFoundException_when_non_existent_id_is_given_with_update() {
        // Arrange
        long nonExistingBookId = 12L;
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", "Auteur du book", "1988", "TestSynopsis");

        when(bookRepository.findById(nonExistingBookId)).thenReturn(Optional.empty());

        // Act et Assert
        org.junit.jupiter.api.Assertions.assertThrows(BookNotFoundException.class, () ->
                bookService.updateBook(nonExistingBookId, bookRequest));

        verify(bookRepository).findById(nonExistingBookId);
    }


    // GET


    @Test
    void should_return_bookDTO_when_book_exists() throws BookNotFoundException {
        // Arrange
        Long bookId = 6L;
        Book book = new Book();
        book.setId(bookId);
        book.setIsbn("1234567890");
        book.setTitre("Titre du book");
        book.setAuteur("Auteur du book");
        book.setDatePublication(null);
        book.setSynopsis("TestSynopsis");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        BookDTO result = bookService.getBookById(bookId);

        // Assert
        verify(bookRepository).findById(bookId);
        Assertions.assertThat(result).isInstanceOf(BookDTO.class);
    }

    @Test
    void should_throw_exception_when_book_does_not_exist() {
        // Arrange
        Long nonExistingBookId = 10L;

        when(bookRepository.findById(nonExistingBookId)).thenReturn(Optional.empty());

        // Act et Assert
        org.junit.jupiter.api.Assertions.assertThrows(BookNotFoundException.class, () ->
                bookService.getBookById(nonExistingBookId));

        verify(bookRepository).findById(nonExistingBookId);
    }

    @Test
    void should_return_books_with_filters_when_books_exist() throws BookNotFoundException {
        // Arrange
        BookingFilters bookingFilters = new BookingFilters("55555", "TestTitre", "TestAuteur", null, "TestSynopsisTest");
        Book book = new Book();
        book.setIsbn("55555");
        book.setTitre("TestTitre");
        book.setAuteur("TestAuteur");
        book.setDatePublication(null);
        book.setSynopsis("TestSynopsisTest");

        List<Book> expectedBooks = List.of(book);

        Mockito.when(bookRepository.findAll(Mockito.<Specification<Book>>any())).thenReturn(expectedBooks);

        // Act
        bookService.search(bookingFilters);

        // Assert
        verify(bookRepository).findAll(Mockito.<Specification<Book>>any());
    }
}
