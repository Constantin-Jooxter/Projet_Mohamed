package com.example.livrebiblio.domain.author;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable long id) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequest authorRequest) throws AuthorNotFoundException {
        return ResponseEntity.ok().body(authorService.createAuthor(authorRequest));
    }
}
