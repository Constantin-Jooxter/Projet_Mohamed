package com.example.livrebiblio.domain.library;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

}
