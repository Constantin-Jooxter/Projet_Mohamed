package com.example.livrebiblio;

import com.example.livrebiblio.domain.author.AuthorBadRequestException;
import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import com.example.livrebiblio.domain.book.BookBadRequestException;
import com.example.livrebiblio.domain.book.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book Not Found");
    }

    @ExceptionHandler(BookBadRequestException.class)
    public ResponseEntity<String> handleBookBadRequestException(BookBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request");
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Author Not Found");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request");
    }

    @ExceptionHandler(AuthorBadRequestException.class)
    public ResponseEntity<String> handleBookBadRequestExceptionAuthor(AuthorBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request : Name and surname are required");
    }
}
