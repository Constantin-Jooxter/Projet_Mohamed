package com.example.livrebiblio.domain.book;

import com.example.livrebiblio.domain.author.AuthorNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // POST

    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookRequest bookRequest) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(bookService.createBook(bookRequest));
    }

    // DELETE

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable @Valid Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<BookRequest> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.updateBook(id, bookRequest));
    }

    // GET

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getBookByIdDTO(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(@ParameterObject BookingFilters bookFilters) throws BookNotFoundException {
        List<BookDTO> books = bookService.search(bookFilters);
        return ResponseEntity.ok().body(books);
    }
}

