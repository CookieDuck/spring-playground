package com.example.controller;

import com.example.service.WordCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StringsController {
    private final WordCounter counter;

    @Autowired
    public StringsController(WordCounter counter) {
        this.counter = counter;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> getWordCount(@RequestBody String input) {
        return counter.count(input);
    }
}
