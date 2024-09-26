package com.example.restaurantrater.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="reviewer_id", nullable = false)
    private Reviewer reviewer;

    // Restaurant FK
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "PEANUT_SCORE")
    private int peanutScore;

    @Column(name = "EGG_SCORE")
    private int eggScore;

    @Column(name = "DAIRY_SCORE")
    private int dairyScore;

    @Column(name = "COMMENTS", nullable = false)
    private String comments;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
    
}
