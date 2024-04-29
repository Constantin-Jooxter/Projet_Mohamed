package com.example.livrebiblio.domain.library;


public class LibraryMapper {
    public static LibraryDTO convertToLibraryDTO(Library library) {
        return new LibraryDTO(
                library.getId(),
                library.getName(),
                library.getAdress()
        );
    }
}
