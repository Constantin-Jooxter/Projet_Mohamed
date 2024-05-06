package com.example.livrebiblio;

import com.example.livrebiblio.domain.author.AuthorBadRequestException;
import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import com.example.livrebiblio.domain.book.BookBadRequestException;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.borrowing.BorrowingBadRequestException;
import com.example.livrebiblio.domain.borrowing.BorrowingNotFoundException;
import com.example.livrebiblio.domain.review.ReviewBadRequestException;
import com.example.livrebiblio.domain.review.ReviewNotFoundException;
import com.example.livrebiblio.domain.users.UserBadRequestException;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // BOOK

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BookBadRequestException.class)
    public ResponseEntity<String> handleBookBadRequestException(BookBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // AUTHOR

    @ExceptionHandler(AuthorBadRequestException.class)
    public ResponseEntity<String> handleBookBadRequestExceptionAuthor(AuthorBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // USER

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<String> handleUserBadRequestExceptionAuthor(UserBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // Borrowing

    @ExceptionHandler(BorrowingNotFoundException.class)
    public ResponseEntity<String> handleBorrowingNotFoundException(BorrowingNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BorrowingBadRequestException.class)
    public ResponseEntity<String> handleBorrowingBadRequestExceptionAuthor(BorrowingBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // Review

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<String> handleReviewNotFoundException(ReviewNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ReviewBadRequestException.class)
    public ResponseEntity<String> handleReviewBadRequestExceptionAuthor(ReviewBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
