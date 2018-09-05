package com.teszt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.teszt.entity.Story;

public interface StoryRepository extends CrudRepository<Story, Long> {
	List<Story> findTop5ByOrderByPostedDesc();

	void deleteById(Long id);
}
