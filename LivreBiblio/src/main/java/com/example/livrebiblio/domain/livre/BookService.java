package com.example.livrebiblio.domain.livre;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

    @NonNull
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::convertToBookDTO)
                .collect(Collectors.toList());
    }


    public List<Book> getBookingFilters(String isbn, String auteur) throws BookNotFoundException {
        List<Book> books = bookRepository.findByIsbnAndAuteur(isbn, auteur);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new BookNotFoundException("Books not found with Author : " + auteur + " and ISBN : " + isbn);
        }
    }
}
