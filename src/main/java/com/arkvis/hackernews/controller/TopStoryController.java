package com.arkvis.hackernews.controller;

import com.arkvis.hackernews.model.Story;
import com.arkvis.hackernews.service.StoryService;
import com.arkvis.hackernews.service.TopStoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TopStoryController {

    private final StoryService storyService;

    public TopStoryController() {
        storyService = new TopStoryService();
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopStories(@RequestParam(defaultValue = "10") Integer limit) {
        List<Story> stories = storyService.getStories(limit);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
