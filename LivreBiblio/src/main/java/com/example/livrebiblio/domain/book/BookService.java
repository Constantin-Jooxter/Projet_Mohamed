package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.Author;
import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import com.example.livrebiblio.domain.author.AuthorRepository;
import com.example.livrebiblio.domain.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    public BookDTO createBook(BookRequest bookRequest) throws AuthorNotFoundException {
        Long authorId = bookRequest.getAuteur();
        return authorRepository.findById(authorId)
                .map(author -> createBookWithAuthor(bookRequest, author))
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    }

    private BookDTO createBookWithAuthor(BookRequest bookRequest, Author author) {
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitre(bookRequest.getTitre());
        book.setDatePublication(Instant.parse(bookRequest.getDatePublication()));
        book.setSynopsis(bookRequest.getSynopsis());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        return new BookDTO(savedBook);
    }

    public void deleteBook(Long id) throws BookNotFoundException {

        if (bookRepository.findById(id).isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        } else {
            bookRepository.deleteById(id);
        }
    }

    public BookRequest updateBook(Long id, BookRequest bookRequest) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = initialiseBook(bookRequest, optionalBook);

            bookRepository.save(book);

            return bookRequest;
        } else {
            throw new BookNotFoundException("Book not found with ID : " + id);
        }
    }

    private static Book initialiseBook(BookRequest bookRequest, Optional<Book> optionalBook) {
        Book book = optionalBook.get();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitre(bookRequest.getTitre());
        book.setDatePublication(Instant.parse(bookRequest.getDatePublication()));
        book.setSynopsis(bookRequest.getSynopsis());
        return book;
    }

    // GET

    public BookDTO getBookById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .map(BookMapper::convertToBookDTO)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID : " + id));
    }


    public List<BookDTO> search(BookingFilters bookingFilters) throws BookNotFoundException {
        Specification<Book> specification = buildSpecification(bookingFilters);
        List<Book> books = bookRepository.findAll(specification);

        if (!books.isEmpty()) {
            return books.stream()
                    .map(BookMapper::convertToBookDTO)
                    .collect(Collectors.toList());
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public Specification<Book> buildSpecification(BookingFilters bookingFilters) {

        return BookSpecificationBuilder.builder()
                .withIsbn(bookingFilters.getIsbn())
                .withTitre(bookingFilters.getTitre())
                .withAuteurName(bookingFilters.getAuthor())
                .withdatePublication(bookingFilters.getDatePublication())
                .withSynopsis(bookingFilters.getSynopsis())
                .build();
    }
}
