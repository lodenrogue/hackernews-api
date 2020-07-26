package com.arkvis.hackernews.service;

import com.arkvis.hackernews.model.Story;
import com.arkvis.hackernews.repository.StoryRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AbstractStoryService implements StoryService {

    private StoryRepository storyRepository;

    public AbstractStoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public List<Story> getAllStories() {
        return getStories(0);
    }

    @Override
    public List<Story> getStories(int limit) {
        return getStoryIds().parallelStream()
                .limit(limit)
                .map(this::getStory)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Story getStory(Long id) {
        try {
            return storyRepository.getStoryById(id).execute().body();
        } catch (IOException ex) {
            return null;
        }
    }

    private List<Long> getStoryIds() {
        try {
            return storyRepository.getStoryIds().execute().body();
        } catch (IOException ex) {
            return Collections.emptyList();
        }
    }
}
