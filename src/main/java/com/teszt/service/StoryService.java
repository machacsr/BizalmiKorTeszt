package com.teszt.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teszt.entity.Story;
import com.teszt.entity.User;
import com.teszt.repository.StoryRepository;

@Service
public class StoryService {
	
	private StoryRepository storyRepo;
	
	@Autowired
	public void setStoryRepo(StoryRepository storyRepo) {
		this.storyRepo = storyRepo;
	}
	
	public List<Story> getStories() {
		return storyRepo.findTop5ByOrderByPostedDesc();
	}
	
	public void saveStory(String title, String content, User user) {
		Story story = new Story(title, content, new Date(), user);
		user.addStory(story);
		storyRepo.save(story);
	}

	public void deleteStory(Long storyId, User user) {
		Optional<Story> story = storyRepo.findById(storyId);
		user.deleteStory(story.get());
		storyRepo.deleteById(storyId);
	}

}
