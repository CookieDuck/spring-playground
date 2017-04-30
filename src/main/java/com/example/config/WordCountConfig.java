package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("wordCount")
public class WordCountConfig {
    private boolean caseSensitive;

    private Words words;

    public List<String> getSkippedWords() {
        return words.getSkip();
    }

    public boolean shouldSkip(String word) {
        return getSkippedWords().contains(word);
    }

    @Data
    public static class Words {
        private List<String> skip;
    }
}
