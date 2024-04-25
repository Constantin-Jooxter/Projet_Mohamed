package com.example.livrebiblio.domain.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String surname;
    private Date birthday;
    //   private String ownBooks;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.birthday = author.getBirthday();
        //   this.ownBooks = author.getOwnBooks();
    }
}

