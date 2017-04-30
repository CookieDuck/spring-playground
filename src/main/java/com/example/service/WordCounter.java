package com.example.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounter {
    public Map<String,Integer> count(String input) {
        Map<String, Integer> result = new LinkedHashMap<>();
        if (input != null && !input.isEmpty()) {
            String[] words = input.replaceAll("[^a-zA-Z ]", "").split("\\s+");
            for (String word : words) {
                result.put(word, result.getOrDefault(word, Integer.valueOf(0)) + 1);
            }
        }
        return result;
    }
}
