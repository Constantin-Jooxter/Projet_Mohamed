package com.example.livrebiblio.domain.livre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookRequest bookRequest) {
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

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/auteur/Test/{auteur}")
    public ResponseEntity<BookDTO> getBookByAuteur(@PathVariable String auteur) throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getBookByAuthor(auteur));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String isbn,
                                                  @RequestParam(required = false) String auteur) throws BookNotFoundException {
        List<Book> books = bookService.getBookingFilters(isbn, auteur);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/AllBook/")
    public ResponseEntity<List<BookDTO>> getAllBooks() throws BookNotFoundException {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
}

