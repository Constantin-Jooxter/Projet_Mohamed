package com.example.livrebiblio.domain.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/library")
public class LibraryController {

    @GetMapping("/{id}")
    public Library findById(@PathVariable Long id) {
        return null;
    }
}