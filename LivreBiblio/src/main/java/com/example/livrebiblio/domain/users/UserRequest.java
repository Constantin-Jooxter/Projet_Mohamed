package com.example.livrebiblio.domain.users;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private String adress;
    private LocalDate registration;
    private LocalDate borrowing;
}
