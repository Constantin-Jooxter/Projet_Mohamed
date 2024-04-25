package com.example.livrebiblio.domain.author;


import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorRequest authorRequest) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(authorService.createAuthor(authorRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorDTO>> searchController(@ParameterObject AuthorFilters authorFilters) throws AuthorNotFoundException {
        List<AuthorDTO> author = authorService.searchAuthor(authorFilters);
        return ResponseEntity.ok().body(author);
    }
}
