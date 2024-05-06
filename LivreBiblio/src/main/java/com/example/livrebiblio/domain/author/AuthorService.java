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
        return authorRepository.findById(id)
                .map(AuthorMapper::convertToAuthorDTO)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found"));
    }

    //Delete

    public void deleteAuthorById(Long id) throws AuthorNotFoundException {
        Author authorEntity = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id " + id));
        authorRepository.delete(authorEntity);
    }

// POST

    public AuthorDTO createAuthor(AuthorRequest authorRequest) throws AuthorBadRequestException {
        Author author = createAuthorFromRequest(authorRequest);
        Optional<Author> savedAuthor = Optional.of(authorRepository.save(author));
        return savedAuthor.map(this::createAuthorDTO)
                .orElseThrow(() -> new AuthorBadRequestException("Failed to create author"));
    }


    private Author createAuthorFromRequest(AuthorRequest authorRequest) throws AuthorBadRequestException {
        if (authorRequest.getName() == null || authorRequest.getName().isEmpty() ||
                authorRequest.getSurname() == null || authorRequest.getSurname().isEmpty()) {
            throw new AuthorBadRequestException("Name and surname are required");
        }
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setSurname(authorRequest.getSurname());
        author.setBirthday(authorRequest.getBirthday());
        return author;
    }


    private AuthorDTO createAuthorDTO(Author author) {
        return new AuthorDTO(author);
    }

    //SEARCH

    public List<AuthorDTO> searchAuthor(AuthorFilters authorFilters) throws AuthorNotFoundException {
        Specification<Author> specification = buildSpecification(authorFilters);
        List<Author> authors = authorRepository.findAll(specification);

        if (!authors.isEmpty()) {
            return authors.stream()
                    .map(AuthorMapper::convertToAuthorDTO)
                    .collect(Collectors.toList());
        } else {
            throw new AuthorNotFoundException("Author not found");
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