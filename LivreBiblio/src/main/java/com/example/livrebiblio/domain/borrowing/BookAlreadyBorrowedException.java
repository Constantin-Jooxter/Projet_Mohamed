package com.example.livrebiblio.domain.borrowing;

public class BookAlreadyBorrowedException extends Exception {
    public BookAlreadyBorrowedException(String message) {super(message);}
}
