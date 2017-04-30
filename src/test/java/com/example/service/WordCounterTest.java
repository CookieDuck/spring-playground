package com.example.service;

import com.example.config.WordCountConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterTest {

    private WordCounter counter;

    @Mock
    private WordCountConfig config;

    @Before
    public void initMock() {
        when(config.isCaseSensitive()).thenReturn(true);
        when(config.shouldSkip(anyString())).thenReturn(false);
    }

    @Before
    public void init() {
        counter = new WordCounter(config);
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

    @Test
    public void testCaseInsensitive() {
        when(config.isCaseSensitive()).thenReturn(false);

        Map<String, Integer> result = counter.count("CALM DOWN THIS SHOUT DOWN");
        assertEquals(4, result.size());
        assertEquals(1, result.get("calm").intValue());
        assertEquals(2, result.get("down").intValue());
        assertEquals(1, result.get("this").intValue());
        assertEquals(1, result.get("shout").intValue());
    }

    @Test
    public void testWithSkips() {
        when(config.shouldSkip("verboten")).thenReturn(true);
        when(config.shouldSkip("forbidden")).thenReturn(true);

        Map<String, Integer> result = counter.count("filter out verboten and forbidden");
        assertEquals(3, result.size());
        assertEquals(1, result.get("filter").intValue());
        assertEquals(1, result.get("out").intValue());
        assertEquals(1, result.get("and").intValue());
        assertFalse(result.containsKey("verboten"));
        assertFalse(result.containsKey("forbidden"));
    }
}
