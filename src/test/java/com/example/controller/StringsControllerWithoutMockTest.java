package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=the",
        "wordCount.words.skip[1]=an"
})
@AutoConfigureMockMvc(secure = false)
public class StringsControllerWithoutMockTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testWordCounter() throws Exception {
        String request = "A brown cow jumps over a brown fox";
        mvc.perform(post("/words/count").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(7)))
                .andExpect(jsonPath("$.A", is(1)))
                .andExpect(jsonPath("$.brown", is(2)))
                .andExpect(jsonPath("$.cow", is(1)))
                .andExpect(jsonPath("$.jumps", is(1)))
                .andExpect(jsonPath("$.over", is(1)))
                .andExpect(jsonPath("$.a", is(1)))
                .andExpect(jsonPath("$.fox", is(1)));
    }

    @Test
    public void testIgnorePunctuation() throws Exception {
        String request = "How now, brown cow";
        mvc.perform(post("/words/count").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(4)))
                .andExpect(jsonPath("$.How", is(1)))
                .andExpect(jsonPath("$.now", is(1)))
                .andExpect(jsonPath("$.brown", is(1)))
                .andExpect(jsonPath("$.cow", is(1)));
    }
}
