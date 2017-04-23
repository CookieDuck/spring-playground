package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MathServiceTest {
    @Test
    public void testAdd() {
        assertEquals("4 + 6 = 10", MathService.generateExpressionAndResult(MathService.Operation.add, 4, 6));
    }

    @Test
    public void testSubtract() {
        assertEquals("4 - 6 = -2", MathService.generateExpressionAndResult(MathService.Operation.subtract, 4, 6));
    }

    @Test
    public void testMultiply() {
        assertEquals("4 * 6 = 24", MathService.generateExpressionAndResult(MathService.Operation.multiply, 4, 6));
    }

    @Test
    public void testDivide() {
        assertEquals("30 / 5 = 6", MathService.generateExpressionAndResult(MathService.Operation.divide, 30, 5));
    }

    @Test
    public void testSum() {
        assertEquals("4 + 5 + 6 = 15", MathService.generateExpressionAndSum(asList(4, 5, 6)));
    }

    @Test
    public void testVolume() {
        assertEquals("The volume of a 2x3x4 rectangle is 24", MathService.generateExpressionAndVolume(2, 3, 4));
    }

    @Test
    public void testAddIsDefault() {
        assertEquals("add", MathService.DEFAULT_OPERATION.toString());
    }
}
