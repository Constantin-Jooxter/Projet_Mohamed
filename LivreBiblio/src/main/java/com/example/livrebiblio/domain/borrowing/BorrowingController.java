package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @GetMapping("/{id}")
    public Borrowing getBorrowingById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<BorrowingDTO> createAuthor(@RequestBody BorrowingRequest borrowingRequest) throws UserNotFoundException, BookNotFoundException {
        return ResponseEntity.ok().body(borrowingService.createBorrowing(borrowingRequest));
    }
}
