package com.example.livrebiblio.domain.author;

import com.example.livrebiblio.domain.book.Book;
import com.example.livrebiblio.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    // private BookService bookService;

    // GET

    public Author getAuthorById(Long id) throws AuthorNotFoundException {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with ID : " + id));
    }

    // POST

    public Author createAuthor(AuthorRequest authorRequest) throws AuthorNotFoundException {
        Author author = new Author();
        BeanUtils.copyProperties(authorRequest, author);

        List<String> bookTitles = Arrays.asList(authorRequest.getOwnBooks().split(","));

        for (String bookTitle : bookTitles) {
            Book book = new Book();
            book.setTitre(bookTitle.trim());
            book.setAuthor(author);
            author.getBooks().add(book);
        }
        return authorRepository.save(author);
    }

}
