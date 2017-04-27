package com.example.repo;

import com.example.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, Long> {
    MovieEntity findByTitle(String title);

    Iterable<MovieEntity> findByYearBetween(Integer from, Integer to);
}
