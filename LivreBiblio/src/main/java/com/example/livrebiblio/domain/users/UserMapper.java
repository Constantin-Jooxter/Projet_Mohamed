package com.example.livrebiblio.domain.users;

public class UserMapper {

    public static UserDTO convertToUserDTO(User users) {
        return new UserDTO(
                users.getName(),
                users.getSurname(),
                users.getAdress(),
                users.getRegistration(),
                users.getBorrowing()
        );
    }
}
