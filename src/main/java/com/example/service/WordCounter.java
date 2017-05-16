package com.example.service;

import com.example.config.WordCountConfig;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WordCounter {
    private final WordCountConfig config;

    public WordCounter(WordCountConfig wordCountConfig) {
        this.config = wordCountConfig;
    }

    public Map<String, Integer> count(String input) {
        Map<String, Integer> result = new LinkedHashMap<>();
        if (input != null && !input.isEmpty()) {
            String[] words = getWords(input);
            for (String word : words) {
                if (!config.shouldSkip(word)) {
                    result.put(word, result.getOrDefault(word, Integer.valueOf(0)) + 1);
                }
            }
        }
        return result;
    }

    private String[] getWords(String input) {
        String punctuationRemoved = input.replaceAll("[^a-zA-Z ]", "");
        if (!config.isCaseSensitive())
            punctuationRemoved = punctuationRemoved.toLowerCase();
        return punctuationRemoved.split("\\s+");
    }
}
