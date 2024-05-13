package com.example.livrebiblio.domain.borrowing;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findAll(Specification<Borrowing> specification);
    boolean existsByBookId(Long bookId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Borrowing b WHERE b.end_date = :end_date")
    boolean existsByEnd_date(LocalDate end_date);
}
