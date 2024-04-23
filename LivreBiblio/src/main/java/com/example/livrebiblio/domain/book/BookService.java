package com.example.livrebiblio.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public BookDTO createBook(BookRequest bookRequest) {
        Book book = new Book();
        BeanUtils.copyProperties(bookRequest, book);

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
            Book book = optionalBook.get();
            BeanUtils.copyProperties(bookRequest, book);

            bookRepository.save(book);

            return bookRequest;
        } else {
            throw new BookNotFoundException("Book not found with ID : " + id);
        }
    }

    // GET

    public BookDTO getBookById(Long id) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return BookMapper.convertToBookDTO(book);
        } else {
            throw new BookNotFoundException("Book not found with ID : " + id);
        }
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
                .withAuteur(bookingFilters.getAuthor())
                .withdatePublication(bookingFilters.getDatePublication())
                .withSynopsis(bookingFilters.getSynopsis())
                .build();
    }
}
