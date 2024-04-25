package com.example.livrebiblio.domain.author;


public class AuthorMapper {
    public static AuthorDTO convertToAuthorDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getFormattedBirthday()
                //   author.getOwnBooks()
        );
    }
}
