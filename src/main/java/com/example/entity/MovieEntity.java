package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor(force = true)
@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Integer year;

    public MovieEntity(String title) {
        this.title = title;
    }

    public MovieEntity(String title, Integer year) {
        this.title = title;
        this.year = year;
    }
}
