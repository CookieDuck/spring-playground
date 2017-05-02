package com.example.controller;

import com.example.entity.MovieEntity;
import com.example.model.OmdbMovie;
import com.example.repo.MovieRepository;
import com.example.service.OmdbService;
import com.example.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieRepository repo;

    private final OmdbService service;

    @Autowired
    public MovieController(MovieRepository movieRepository, OmdbService service) {
        this.repo = movieRepository;
        this.service = service;
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

    @GetMapping
    public List<OmdbMovie> queryOmdb(@RequestParam(value = "q") String query) {
        return MapperUtils.mapFromResponse(service.searchOmdb(query));
    }
}
