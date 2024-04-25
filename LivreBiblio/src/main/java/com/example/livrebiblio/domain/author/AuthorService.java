package com.example.livrebiblio.domain.author;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;


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

    public AuthorDTO createAuthor(AuthorRequest authorRequest) {
        Author author = createAuthorFromRequest(authorRequest);
        Author savedAuthor = authorRepository.save(author);
        return createAuthorDTO(savedAuthor);
    }

    private Author createAuthorFromRequest(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setSurname(authorRequest.getSurname());
        author.setBirthday(authorRequest.getBirthday());
        return author;
    }

    private AuthorDTO createAuthorDTO(Author author) {
        return new AuthorDTO(author);
    }

    public List<AuthorDTO> searchAuthor(AuthorFilters authorFilters) throws AuthorNotFoundException {
        Specification<Author> specification = buildSpecification(authorFilters);
        List<Author> authors = authorRepository.findAll(specification);

        if (!authors.isEmpty()) {
            return authors.stream()
                    .map(AuthorMapper::convertToAuthorDTO)
                    .collect(Collectors.toList());
        } else {
            throw new AuthorNotFoundException("Book not found");
        }
    }

    public Specification<Author> buildSpecification(AuthorFilters authorsFilters) {

        return AuthorSpecificationBuilder.builder()
                .withName(authorsFilters.getName())
                .withSurname(authorsFilters.getSurname())
                .withBirthday(authorsFilters.getBirthday())
                .build();
    }
}





    /*

    public AuthorDTO createAuthor(AuthorRequest authorRequest) {
        Author author = createAuthorFromRequest(authorRequest);
        if (authorRequest.getOwnBooks() != null) {
            associateBooksToAuthor(author, List.of(authorRequest.getOwnBooks().split(",")));
        }
    Author savedAuthor = authorRepository.save(author);
        return createAuthorDTO(savedAuthor);
    }



    private Book createBookWithTitle(String bookTitle, Author author) {
        Book book = new Book();
        book.setTitre(bookTitle);
        book.setAuthor(author);
        return book;
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
    }*/
