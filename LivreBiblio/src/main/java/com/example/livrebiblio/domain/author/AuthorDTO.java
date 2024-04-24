package com.example.livrebiblio.domain.author;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private String name;
    private String surname;
    private Instant birthday;
    private String ownBooks;

    public AuthorDTO(Author author) {
        this.name = author.getName();
        this.surname = author.getSurname();
        this.birthday = author.getBirthday();
        this.ownBooks = author.getOwnBooks();
    }
}

