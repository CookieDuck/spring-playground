package com.example.service;

import com.example.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class WordCounterTest {

    private WordCounter counter;

    @Before
    public void init() {
        counter = new WordCounter();
    }

    @Test
    public void testNullMapsToEmpty() {
        Map<String, Integer> result = counter.count(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyMapsToEmpty() {
        Map<String, Integer> result = counter.count("");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPunctuationIgnored() {
        Map<String, Integer> result = counter.count("Hey, let us ignore; this awful sentence.");
        assertEquals(7, result.size());
    }

    @Test
    public void testRepeats() {
        Map<String, Integer> result = counter.count("buffalo buffalo buffalo buffalo buffalo");
        assertEquals(1, result.size());
    }

    @Test
    public void testBasicHappyPath() {
        Map<String, Integer> result = counter.count("A brown cow jumps over a brown fox");
        assertEquals(7, result.size());
        assertEquals(1, result.get("A").intValue());
        assertEquals(2, result.get("brown").intValue());
        assertEquals(1, result.get("cow").intValue());
        assertEquals(1, result.get("jumps").intValue());
        assertEquals(1, result.get("over").intValue());
        assertEquals(1, result.get("a").intValue());
        assertEquals(1, result.get("fox").intValue());
    }
}
