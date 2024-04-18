package com.example.livrebiblio.domain.livre;

public class BookBadRequestException extends Exception {
    public BookBadRequestException(String message) {
        super(message);
    }
}
