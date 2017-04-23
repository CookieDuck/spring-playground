package com.example.controller;

import com.example.service.MathService;
import com.example.service.MathService.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPi() throws Exception {
        mvc.perform(get("/math/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testAdd() throws Exception {
        mvc.perform(get(buildRequestString(Operation.add, 4, 6)))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testSubtract() throws Exception {
        mvc.perform(get(buildRequestString(Operation.subtract, 4, 6)))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testMultiply() throws Exception {
        mvc.perform(get(buildRequestString(Operation.multiply, 4, 6)))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testDivide() throws Exception {
        mvc.perform(get(buildRequestString(Operation.divide, 30, 5)))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testNoOperationResolvesToAdd() throws Exception {
        mvc.perform(get(buildRequestString(null, 30, 5)))
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void testSum() throws Exception {
        mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testVolumeAcceptsAllVerbs() throws Exception {
        String url = "/math/volume/42/56/79";
        mvc.perform(get(url)).andExpect(status().isOk());
        mvc.perform(post(url)).andExpect(status().isOk());
        mvc.perform(put(url)).andExpect(status().isOk());
        mvc.perform(patch(url)).andExpect(status().isOk());
        mvc.perform(delete(url)).andExpect(status().isOk());
    }

    @Test
    public void testVolume() throws Exception {
        mvc.perform(get("/math/volume/6/7/8"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

    private static String buildRequestString(Operation operation, int x, int y) {
        StringBuilder builder = new StringBuilder("/math/calculate?operation=");
        builder.append(operation != null ? operation.toString() : MathService.DEFAULT_OPERATION);
        builder.append("&x=").append(x);
        builder.append("&y=").append(y);
        return builder.toString();

    }
}
