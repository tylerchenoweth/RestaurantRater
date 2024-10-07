package com.example.restaurantrater.services;

import com.example.restaurantrater.entities.Reviewer;
import com.example.restaurantrater.repositories.ReviewerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public ReviewerService(ReviewerRepository reviewerRepository, PasswordEncoder passwordEncoder) {
        this.reviewerRepository = reviewerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Reviewer registerReviewer(Reviewer reviewer) {
        // Encode the password before saving
        reviewer.setPassword(passwordEncoder.encode(reviewer.getPassword()));
        return reviewerRepository.save(reviewer);
    }

    public Reviewer findByUsername(String username) {
        return reviewerRepository.findByUsername(username).orElse(null);
    }
}
