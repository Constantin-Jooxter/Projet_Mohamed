package com.example.livrebiblio.domain.author;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private String name;
    private String surname;
    private Date birthday;
    private String ownBooks;

    public AuthorDTO(Author author) {
        this.name = author.getName();
        this.surname = author.getSurname();
        this.birthday = author.getBirthday();
        this.ownBooks = author.getOwnBooks();
    }
}

