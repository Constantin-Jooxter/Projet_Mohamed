package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.Author;
import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import com.example.livrebiblio.domain.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    // POST

    public BookDTO createBook(BookRequest bookRequest) throws AuthorNotFoundException {
        Long authorId = bookRequest.getAuthor();
        return authorRepository.findById(authorId)
                .map(author -> createBookWithAuthor(bookRequest, author))
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    }

    public BookDTO createBookWithAuthor(BookRequest bookRequest, Author author) {
        Book book = createBook(bookRequest, author);
        return saveBook(book);
    }

    private Book createBook(BookRequest bookRequest, Author author) {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setDatePublication(bookRequest.getDatePublication());
        book.setSynopsis(bookRequest.getSynopsis());
        book.setType(bookRequest.getType());
        book.setAuthor(author);
        return book;
    }

    private BookDTO saveBook(Book book) {

        Book savedBook = bookRepository.save(book);
        return new BookDTO(savedBook);
    }

    // Delete

    public void deleteBook(Long id) throws BookNotFoundException {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
        bookRepository.deleteById(id);
    }

    // PUT

    public BookRequest updateBook(Long id, BookRequest bookRequest) throws BookNotFoundException {
        Book book = initialiseBook(id, bookRequest);

        bookRepository.save(book);

        return bookRequest;
    }

    private Book initialiseBook(Long id, BookRequest bookRequest) throws BookNotFoundException {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setIsbn(bookRequest.getIsbn());
                    book.setTitle(bookRequest.getTitle());
                    book.setDatePublication(bookRequest.getDatePublication());
                    book.setSynopsis(bookRequest.getSynopsis());
                    book.setType(bookRequest.getType());
                    return book;
                })
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID : " + id));
    }

    // GET

    public BookDTO getBookByIdDTO(Long id) throws BookNotFoundException {
        return getBookById(id)
                .map(BookMapper::convertToBookDTO)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID : " + id));
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<BookDTO> search(BookingFilters bookingFilters) throws BookNotFoundException {
        Specification<Book> specification = buildSpecification(bookingFilters);
        List<Book> books = bookRepository.findAll(specification);

        if (!books.isEmpty()) {
            return books.stream()
                    .map(BookMapper::convertToBookDTO)
                    .toList();
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public Specification<Book> buildSpecification(BookingFilters bookingFilters) {

        return BookSpecificationBuilder.builder()
                .withIsbn(bookingFilters.getIsbn())
                .withTitle(bookingFilters.getTitle())
                .withAuthorName(bookingFilters.getAuthor())
                .withdatePublication(bookingFilters.getDatePublication())
                .withSynopsis(bookingFilters.getSynopsis())
                .withType(bookingFilters.getType())
                .build();
    }
}
