package com.example.livrebiblio.domain.users;

public class UserBadRequestException extends Exception {
    public UserBadRequestException(String message) {
        super(message);
    }
}
