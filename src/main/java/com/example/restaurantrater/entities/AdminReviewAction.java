package com.example.restaurantrater.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;



public class AdminReviewAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="review_id", nullable = false)
    private Review review;

    @Column(name = "IS_APPROVED")
    private boolean isApproved;
}