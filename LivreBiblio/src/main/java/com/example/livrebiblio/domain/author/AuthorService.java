package com.example.livrebiblio.domain.author;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // GET

    public AuthorDTO getAuthorById(Long id) throws AuthorNotFoundException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author authorEntity = author.get();
            return AuthorMapper.convertToAuthorDTO(authorEntity);
        } else {
            throw new AuthorNotFoundException("Author not found");
        }
    }


    // POST

    public AuthorDTO createAuthor(AuthorRequest authorRequest) throws AuthorNotFoundException {

        Author author = createAuthorFromRequest(authorRequest);
        associateBooksToAuthor(author, List.of(authorRequest.getOwnBooks().split(",")));
        Author savedAuthor = authorRepository.save(author);
        return createAuthorDTO(savedAuthor);
    }


    private void associateBooksToAuthor(Author author, List<String> bookTitles) {
        for (String bookTitle : bookTitles) {
            String trimmedTitle = bookTitle.trim();

            Book existingBook = bookRepository.findByTitre(trimmedTitle);

            if (existingBook != null) {
                if (existingBook.getAuthor() == null) {
                    existingBook.setAuthor(author);
                    author.getBooks().add(existingBook);
                }
            } else {
                Book newBook = createBookWithTitle(trimmedTitle, author);
                author.getBooks().add(newBook);
            }
        }
    }

    private Author createAuthorFromRequest(AuthorRequest authorRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(authorRequest, author);
        return author;
    }

    private AuthorDTO createAuthorDTO(Author author) {
        return new AuthorDTO(author);
    }

    private Book createBookWithTitle(String bookTitle, Author author) {
        Book book = new Book();
        book.setTitre(bookTitle);
        book.setAuthor(author);
        return book;
    }
}
