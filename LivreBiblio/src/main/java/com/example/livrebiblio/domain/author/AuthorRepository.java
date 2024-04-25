package com.example.livrebiblio.domain.author;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Nonnull
    List<Author> findAll();

    List<Author> findAll(Specification<Author> specification);
}
