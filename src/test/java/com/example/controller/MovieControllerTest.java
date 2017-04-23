package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testMoviePatch() throws Exception {
        mvc.perform(patch("/movies/12/update?title=Curious George"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\": 12, \"title\": \"Curious George\"}"));
    }
}
