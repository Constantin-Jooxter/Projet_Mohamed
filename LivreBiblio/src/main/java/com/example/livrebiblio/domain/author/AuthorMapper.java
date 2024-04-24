package com.example.livrebiblio.domain.author;

public class AuthorMapper {
    public static AuthorDTO convertToAuthorDTO(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getSurname(),
                author.getBirthday(),
                author.getOwnBooks()
        );
    }
}
