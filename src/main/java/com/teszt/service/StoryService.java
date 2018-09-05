package com.teszt.service;

import java.util.Date;
import java.util.List;

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
		storyRepo.save(new Story(title, content, new Date(), user));
	}

	public void deleteStory(Long storyId) {
		storyRepo.deleteById(storyId);
	}

}
