package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.BookNotFoundException;
import com.example.livrebiblio.domain.users.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/borrowing")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BorrowingRepository borrowingRepository;

    @PostMapping("/")
    public ResponseEntity<BorrowingDTO> createMyBorrowing(@RequestBody BorrowingRequest borrowingRequest) throws UserNotFoundException, BookNotFoundException, BookAlreadyBorrowedException {
        return ResponseEntity.ok().body(borrowingService.createBorrowing(borrowingRequest));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<BorrowingDTO> patchBorrowing(@RequestBody BorrowingRequest borrowingRequest, @PathVariable Long id) throws BorrowingNotFoundException {
        return ResponseEntity.ok().body(borrowingService.patchBorrowingReturnDate(borrowingRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDTO> getBorrowingById(@PathVariable Long id) throws BorrowingNotFoundException {
        return ResponseEntity.ok().body(borrowingService.getBorrowingDTO(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BorrowingDTO>> searchBooks(@ParameterObject BorrowingFilters borrowingFilters) throws BorrowingNotFoundException {
        List<BorrowingDTO> borrowings = borrowingService.search(borrowingFilters);
        return ResponseEntity.ok().body(borrowings);
    }
}
