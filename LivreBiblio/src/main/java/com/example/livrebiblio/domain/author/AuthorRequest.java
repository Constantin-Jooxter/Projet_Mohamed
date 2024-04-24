package com.example.livrebiblio.domain.author;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthorRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private Instant birthday;
    @NotBlank
    private String ownBooks;
    private List<String> bookTitles;
}
