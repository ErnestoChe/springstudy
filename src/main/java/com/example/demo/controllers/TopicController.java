package com.example.demo.controllers;

import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TopicController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topic/{id}")
    public String addTopic(
            @AuthenticationPrincipal User user,
            @RequestParam String filter,
            Map<String, Object> map,
            @PathVariable String id
    ) {
        Iterable<Message> messages = messageRepository.findAll();
        if (filter!= null && !filter.isEmpty()) {
            messages = messageRepository.findByTopic(filter);
        } else {
            messages = messageRepository.findAll();
        }
        map.put("messages", messages);
        return "topic";
    }

    @PostMapping("/topic/{id}")
    public String readFromTopic(
            @AuthenticationPrincipal User user,
            @RequestParam String filter,
            @RequestParam String text,
            Map<String, Object> map,
            @PathVariable String id
    ){
        Iterable<Message> messages;
        if (filter!= null && !filter.isEmpty()) {
            messages = messageRepository.findByTopic(filter);
        } else {
            messages = messageRepository.findAll();
        }
        Message msg = new Message();
        msg.setTextMessage(text);
        msg.setAuthor(user);
        msg.setTopic(topicRepository.findByThemeName(filter));
        messageRepository.save(msg);
        map.put("messages", messages);
        return "main";
    }
}
