package com.example.controller;

import com.example.entity.MovieEntity;
import com.example.repo.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MovieRepository repo;

    @Test
    @Transactional
    public void testMoviePatch() throws Exception {
        MovieEntity target = repo.save(new MovieEntity("Furious George"));

        mvc.perform(patch("/movies/" + target.getId().intValue() + "/update")
                .param("title", "Curious George"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": " + target.getId().intValue() + ", \"title\": \"Curious George\"}"));
    }

    @Test
    @Transactional
    public void testFindByTitle() throws Exception {
        repo.save(new MovieEntity("Furious George"));

        mvc.perform(get("/movies/find/Furious George"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Furious George")));
    }

    @Test
    @Transactional
    public void testGetMoviesBetweenYear() throws Exception {
        repo.save(new MovieEntity("Movie Uno", 1999));
        repo.save(new MovieEntity("Movie Dos", 2012));
        repo.save(new MovieEntity("In Production", 2025));
        repo.save(new MovieEntity("Shouldn't Find Me", 1980));

        mvc.perform(get("/movies/between")
                .param("year1", "1990")
                .param("year2", "2013"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title", contains("Movie Uno", "Movie Dos")))
                .andExpect(jsonPath("$..year", contains(1999, 2012)))
                .andExpect(jsonPath("$.length()", is(2)));
    }
}
