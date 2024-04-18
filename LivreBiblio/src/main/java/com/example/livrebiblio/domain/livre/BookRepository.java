package com.example.livrebiblio.domain.livre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBooksByIsbn(String isbn);

    Book findBookByAuteur(String auteur);

    // List<Book> searchBookByIsbnAndAuteur(String isbn, String auteur);

    @NonNull
    List<Book> findAll();


}

