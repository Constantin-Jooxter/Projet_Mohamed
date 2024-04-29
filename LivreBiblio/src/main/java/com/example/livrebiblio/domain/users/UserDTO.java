package com.example.livrebiblio.domain.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String surname;
    private String adress;
    private LocalDate registrationDate;
    private LocalDate borrowing;

    public UserDTO(Users users) {
        this.name = users.getName();
        this.surname = users.getSurname();
        this.adress = users.getAdress();
        this.registrationDate = users.getRegistration();
        this.borrowing = users.getBorrowing();
    }
}
