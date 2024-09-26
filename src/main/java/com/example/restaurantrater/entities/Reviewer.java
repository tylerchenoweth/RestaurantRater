package com.example.restaurantrater.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "REVIEWER")
@Getter
@Setter
public class Reviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Size(min=2, max=2)
    @Column(name = "STATE", length=2, nullable = false)
    private String state;

    @Column(name = "ZIPCODE", nullable = false)
    private int zipcode;

    @Column(name = "PEANUT_ALLERGY_INTEREST", nullable = false)
    private boolean peanutAllergyInterest;

    @Column(name = "EGG_ALLERGY_INTEREST", nullable = false)
    private boolean eggAllergyInterest;

    @Column(name = "DAIRY_ALLERGY_INTEREST", nullable = false)
    private boolean dairyAllergyInterest;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean isAdmin;
}
