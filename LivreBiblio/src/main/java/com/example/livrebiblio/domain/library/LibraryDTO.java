package com.example.livrebiblio.domain.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {
    private Long id;
    private String name;
    private String adress;

    public LibraryDTO(Library library) {
        this.id = library.getId();
        this.name = library.getName();
        this.adress = library.getAdress();
    }
}
