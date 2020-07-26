package com.arkvis.hackernews.controller;

import com.arkvis.hackernews.model.Story;
import com.arkvis.hackernews.service.NewStoryService;
import com.arkvis.hackernews.service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class NewStoryController {

    private final StoryService storyService;

    public NewStoryController() {
        storyService = new NewStoryService();
    }

    @GetMapping("/new")
    public ResponseEntity<?> getNewStories() {
        List<Story> stories = storyService.getAllStories();
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
