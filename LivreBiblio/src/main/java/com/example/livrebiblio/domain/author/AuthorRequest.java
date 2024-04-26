package com.example.livrebiblio.domain.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    private String name;
    private String surname;
    private LocalDate birthday;
}
