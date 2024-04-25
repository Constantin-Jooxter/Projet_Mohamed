package com.example.livrebiblio.domain.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String surname;
    private String formattedBirthday;
    //   private String ownBooks;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.surname = author.getSurname();
        this.formattedBirthday = author.getFormattedBirthday();
        //   this.ownBooks = author.getOwnBooks();
    }
}

