package com.example.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "wordCount.caseSensitive=true",
        "wordCount.words.skip[0]=test",
        "wordCount.words.skip[1]=property",
        "wordCount.words.skip[2]=source"
})
@SpringBootTest
public class WordCountConfigTest {
    @Autowired
    private WordCountConfig config;

    @Test
    public void verifyTestPropertySourceWasUsed() {
        assertTrue(config.isCaseSensitive());
        assertEquals(3, config.getSkippedWords().size());
        assertEquals("test", config.getSkippedWords().get(0));
        assertEquals("property", config.getSkippedWords().get(1));
        assertEquals("source", config.getSkippedWords().get(2));
    }
}
