package com.example.livrebiblio.domain.author;


import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable long id) throws AuthorNotFoundException {
        authorService.deleteAuthorById(id);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok().body(authorService.createAuthor(authorRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorDTO>> searchController(@ParameterObject AuthorFilters authorFilters) throws AuthorNotFoundException {
        List<AuthorDTO> author = authorService.searchAuthor(authorFilters);
        return ResponseEntity.ok().body(author);
    }
}
