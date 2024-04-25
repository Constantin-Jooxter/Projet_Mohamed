package com.example.livrebiblio.domain.author;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AuthorFilters {
    @Parameter(name = "name", description = "Search by Name")
    private String name;
    @Parameter(name = "surname", description = "Search by Surname")
    private String surname;
    @Parameter(name = "birthday", description = "Search by birthday", example = "yyyy-MM-dd")
    private Date birthday;
}
