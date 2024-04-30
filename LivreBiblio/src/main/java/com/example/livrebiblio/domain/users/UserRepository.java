package com.example.livrebiblio.domain.users;

import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    List<User> findAll();

    List<User> findAll(Specification<User> specification);
}
