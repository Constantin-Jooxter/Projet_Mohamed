package com.example.livrebiblio.domain.livre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.function.Predicate;
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

    public BookDTO getBookByIsbn(String isbn) throws BookNotFoundException {
        Book book = bookRepository.findBooksByIsbn(isbn);
        if (book != null) {
            return BookMapper.convertToBookDTO(book);
        } else {
            throw new BookNotFoundException("Book not found with ISBN : " + isbn);
        }
    }

    public BookDTO getBookByAuthor(String auteur) throws BookNotFoundException {
        Book book = bookRepository.findBookByAuteur(auteur);
        if (book != null) {
            return BookMapper.convertToBookDTO(book);
        } else {
            throw new BookNotFoundException("Book not found with Author : " + auteur);
        }
    }

    public List<BookDTO> search(BookingFilters bookingFilters) throws BookNotFoundException {
        Specification<Book> specification = (root, query, criteriaBuilder) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (bookingFilters.getIsbn() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isbn"), bookingFilters.getIsbn()));
            }
            if (bookingFilters.getAuteur() != null) {
                predicates.add(criteriaBuilder.equal(root.get("auteur"), bookingFilters.getAuteur()));
            }
            if (bookingFilters.getTitre() != null) {
                predicates.add(criteriaBuilder.equal(root.get("titre"), bookingFilters.getTitre()));
            }
            if (bookingFilters.getSynopsis() != null) {
                predicates.add(criteriaBuilder.equal(root.get("synopsis"), bookingFilters.getSynopsis()));
            }
            if (bookingFilters.getDatePublication() != null) {
                predicates.add(criteriaBuilder.equal(root.get("datePublication"), bookingFilters.getDatePublication()));
            }

            //patern builder --> me renseigner

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };

        List<Book> books = bookRepository.findAll(specification);
        if (!books.isEmpty()) {
            return books.stream()
                    .map(BookMapper::convertToBookDTO)
                    .collect(Collectors.toList());
        } else {
            throw new BookNotFoundException("No books found.");
        }
    }
}
