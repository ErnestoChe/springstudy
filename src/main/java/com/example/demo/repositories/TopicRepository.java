package com.example.demo.repositories;

import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    Topic findByThemeName(String theme_name);

}
