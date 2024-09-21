package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// import lombok.Getter;
// import lombok.Setter;

// import javax.persistence.Entity;
// import javax.persistence.Table;
// import javax.persistence.Column;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;

@Entity
@Table(name = "restaurant")
// @Getter
// @Setter
public class Restaurant {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    
}
