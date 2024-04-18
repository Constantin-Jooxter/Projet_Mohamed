package com.example.livrebiblio.domain.livre;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findBooksByIsbn(String isbn);

    Book findBookByAuteur(String auteur);

    @NonNull
    List<Book> findAll();
}
