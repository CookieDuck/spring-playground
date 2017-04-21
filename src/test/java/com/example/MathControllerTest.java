package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPi() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/math/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }
}
