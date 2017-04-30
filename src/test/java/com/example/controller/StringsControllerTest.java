package com.example.controller;

import com.example.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StringsController.class)
public class StringsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WordCounter counter;

    @Before
    public void initMock() {
        Map<String, Integer> mockMap = new HashMap<>();
        mockMap.put("I", 1);
        mockMap.put("openly", 1);
        mockMap.put("mock", 1);
        mockMap.put("thee", 1);

        when(counter.count(anyString())).thenReturn(mockMap);
    }

    @Test
    public void testMockingWorked() throws Exception {
        mvc.perform(post("/words/count").contentType(MediaType.APPLICATION_JSON).content("dont matta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(4)))
                .andExpect(jsonPath("$.I", is(1)))
                .andExpect(jsonPath("$.openly", is(1)))
                .andExpect(jsonPath("$.mock", is(1)))
                .andExpect(jsonPath("$.thee", is(1)));
    }
}
