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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "REVIEWER")
@Getter
@Setter
public class Reviewer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Size(min = 2, max = 2)
    @Column(name = "STATE", length = 2, nullable = false)
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

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    // Implementing UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return isAdmin
                ? Collections.singleton(() -> "ROLE_ADMIN")
                : Collections.singleton(() -> "ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

   @Override
    public boolean isAccountNonLocked() {
        return true;
    }

   @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

   @Override
    public boolean isEnabled() {
        return true;
    }
}
