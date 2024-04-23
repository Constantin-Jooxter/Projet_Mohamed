package com.example.livrebiblio;

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
    public ResponseEntity<String> handleResourceNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Not Found");
    }

    @ExceptionHandler(BookBadRequestException.class)
    public ResponseEntity<String> badRequestException(BookBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> argument_not_valid_exception(BookBadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad Request");
    }
}
