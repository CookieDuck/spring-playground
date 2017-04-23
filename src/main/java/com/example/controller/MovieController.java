package com.example.controller;

import com.example.model.Movie;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @PatchMapping(value = "/movies/{id}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie updateTitle(@PathVariable Integer id, @RequestParam(value = "title") String newTitle) {
        //TODO have a backend
        return Movie.builder().id(id).title(newTitle).build();
    }
}
