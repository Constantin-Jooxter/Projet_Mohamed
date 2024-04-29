package com.example.livrebiblio.domain.users;

import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @NonNull
    List<Users> findAll();

    List<Users> findAll(Specification<Users> specification);
}
