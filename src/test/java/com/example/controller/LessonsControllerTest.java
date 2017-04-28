package com.example.controller;

import com.example.entity.Lesson;
import com.example.repo.LessonRepository;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LessonRepository repo;

    @Test
    @Transactional
    public void testCreateLesson() throws Exception {
        mvc.perform(post("/lessons")
                .contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"Should see me\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title", is("Should see me")));
    }

    @Test
    @Transactional
    public void testGetLesson() throws Exception {
        Lesson target = repo.save(new Lesson("Look for me!"));
        mvc.perform(get("/lessons/" + target.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Look for me!")));
    }

    @Test
    @Transactional
    public void testPatchLesson() throws Exception {
        Lesson target = repo.save(new Lesson("Mutate me!"));
        Lesson patchLesson = new Lesson("Should see this");
        patchLesson.setId(target.getId());

        MockHttpServletRequestBuilder patchRequest = MockMvcRequestBuilders.patch("/lessons/" + target.getId())
                .contentType(MediaType.APPLICATION_JSON).content(new GsonBuilder().create().toJson(patchLesson));

        mvc.perform(patchRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(target.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Should see this")));
    }

    @Test
    @Transactional
    public void testDeleteLesson() throws Exception {
        Lesson target = repo.save(new Lesson("Exterminate me!"));
        assertNotNull(repo.findOne(target.getId()));

        mvc.perform(delete("/lessons/" + target.getId()))
                .andExpect(status().isOk());

        assertNull(repo.findOne(target.getId()));
    }

    @Test
    @Transactional
    public void testGetAllLessons() throws Exception {
        repo.save(new Lesson("un"));
        repo.save(new Lesson("deux"));
        repo.save(new Lesson("trois"));

        mvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title", contains("un", "deux", "trois")))
                .andExpect(jsonPath("$.length()", is(3)));
    }
}
