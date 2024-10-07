package com.example.restaurantrater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restaurantrater.entities.Reviewer;
import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
    Optional<Reviewer> findByUsername(String username);
}
