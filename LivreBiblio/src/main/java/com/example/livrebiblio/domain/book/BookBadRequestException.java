package com.example.livrebiblio.domain.book;

public class BookBadRequestException extends Exception {
    public BookBadRequestException(String message) {
        super(message);
    }
}
