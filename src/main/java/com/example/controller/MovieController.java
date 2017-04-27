package com.example.controller;

import com.example.entity.MovieEntity;
import com.example.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository repo;

    public MovieController(MovieRepository movieRepository) {
        this.repo = movieRepository;
    }

    @PatchMapping(value = "/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieEntity updateTitle(@PathVariable Long id, @RequestParam(value = "title") String newTitle) {
        MovieEntity entity = repo.findOne(id);
        entity.setTitle(newTitle);
        return repo.save(entity);
    }

    @GetMapping("/find/{title}")
    public MovieEntity findByTitle(@PathVariable String title) {
        return repo.findByTitle(title);
    }

    @GetMapping("/between")
    public Iterable<MovieEntity> getMoviesWithinDates(
            @RequestParam(value = "year1") Integer from,
            @RequestParam(value = "year2") Integer to
    ) {
        return repo.findByYearBetween(from, to);
    }
}
