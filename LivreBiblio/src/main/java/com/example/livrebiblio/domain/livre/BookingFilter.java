package com.example.livrebiblio.domain.livre;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingFilter {
    private String isbn;
    private String auteur;
    private List<BookDTO> books;
}
