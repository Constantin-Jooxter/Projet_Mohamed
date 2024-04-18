package com.example.livrebiblio.domain.livre;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBooksByIsbn(String isbn);

    Book findBookByAuteur(String auteur);

    List<Book> findByIsbnAndAuteur(String isbn, String auteur);

    @NonNull
    List<Book> findAll();
}
