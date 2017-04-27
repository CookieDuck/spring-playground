package com.example.controller;

import com.example.entity.Lesson;
import com.example.repo.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    @Autowired
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Lesson read(@PathVariable Long id) {
        return this.repository.findOne(id);
    }

    @PatchMapping("/{id}")
    public Lesson update(@PathVariable Long id, @Valid @RequestBody Lesson patch) {
        Lesson target = this.repository.findOne(id);
        target.setTitle(patch.getTitle());
        return this.repository.save(target);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.repository.delete(id);
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }
}
