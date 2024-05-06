package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.Author;
import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import com.example.livrebiblio.domain.author.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    // POST

    @Test
    void should_create_book_when_bookRequest_is_given() throws AuthorNotFoundException {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("Antoine");
        author.setSurname("Dirot");
        author.setBirthday(LocalDate.of(2024, 04, 30));

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));


        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        Book savedBook = new Book();
        savedBook.setIsbn(bookRequest.getIsbn());
        savedBook.setTitle(bookRequest.getTitle());
        savedBook.setAuthor(author);
        savedBook.setDatePublication(bookRequest.getDatePublication());
        savedBook.setSynopsis(bookRequest.getSynopsis());
        savedBook.setType(bookRequest.getType());

        when(bookRepository.save(savedBook)).thenReturn(savedBook);

        // Act
        BookDTO result = bookService.createBook(bookRequest);

        // Assert
        BookDTO expectedDTO = new BookDTO(savedBook.getIsbn(), savedBook.getTitle(), "Antoine Dirot", savedBook.getDatePublication(), savedBook.getSynopsis(), savedBook.getType());
        Assertions.assertThat(result).isEqualTo(expectedDTO);
    }

    @Test
    void should_throw_exception_when_BadRequest_is_given() {
        // Arrange
        Author author = new Author();

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");

        // Act & Assert
        AuthorNotFoundException exception = assertThrows(AuthorNotFoundException.class, () -> bookService.createBookWithAuthor(bookRequest, author));

        // Assert
        assertEquals("Author not found", exception.getMessage());
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
        assertThrows(BookNotFoundException.class, () -> {
            bookService.deleteBook(nonExistingBookId);
        });
    }


    // PUT


    @Test
    void should_update_book_when_bookRequest_is_given() throws BookNotFoundException {
        // Arrange
        Long bookId = 6L;
        Author author = new Author();
        author.setName("test");
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");
        Book savedBook = new Book();
        savedBook.setId(bookId);
        savedBook.setIsbn("1234567890");
        savedBook.setTitle("Titre du book");
        savedBook.setAuthor(author);
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
        BookRequest bookRequest = new BookRequest("1234567890", "Titre du book", 1L, LocalDate.of(2024, 04, 30), "TestSynopsis", "Fantasy");

        when(bookRepository.findById(nonExistingBookId)).thenReturn(Optional.empty());

        // Act et Assert
        assertThrows(BookNotFoundException.class, () ->
                bookService.updateBook(nonExistingBookId, bookRequest));

        verify(bookRepository).findById(nonExistingBookId);
    }


    // GET


    @Test
    void should_return_bookDTO_when_book_exists() throws BookNotFoundException {
        // Arrange
        Long bookId = 6L;
        Author author = new Author();
        author.setName("test");
        Book book = new Book();
        book.setId(bookId);
        book.setIsbn("1234567890");
        book.setTitle("Titre du book");
        book.setAuthor(author);
        book.setDatePublication(null);
        book.setSynopsis("TestSynopsis");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        BookDTO result = bookService.getBookByIdDTO(bookId);

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
        assertThrows(BookNotFoundException.class, () ->
                bookService.getBookByIdDTO(nonExistingBookId));

        verify(bookRepository).findById(nonExistingBookId);
    }

    @Test
    void should_return_books_with_filters_when_books_exist() throws BookNotFoundException {
        // Arrange
        Author author = new Author();
        author.setName("test");
        BookingFilters bookingFilters = new BookingFilters("55555", "TestTitre", "1L", null, "TestSynopsisTest", "Fantasy");
        Book book = new Book();
        book.setIsbn("55555");
        book.setTitle("TestTitre");
        book.setAuthor(author);
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
