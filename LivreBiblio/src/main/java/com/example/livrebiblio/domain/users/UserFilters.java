package com.example.livrebiblio.domain.users;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserFilters {
    @Parameter(name = "Name", example = "Martin")
    private String name;
    @Parameter(name = "Surname", example = "Dupont")
    private String surname;
    @Parameter(name = "Registration", example = "24-05-2024")
    private LocalDate registration;
    @Parameter(name = "BorrowingDate", example = "24-05-2024")
    private LocalDate borrowing;

}
