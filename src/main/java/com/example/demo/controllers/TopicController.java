package com.example.demo.controllers;

import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TopicController {

    private final MessageRepository messageRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public TopicController(MessageRepository messageRepository, TopicRepository topicRepository){
        this.messageRepository = messageRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/topic")
    public String addTopic(
            @AuthenticationPrincipal User user,
            @RequestParam (required = false)String filter,
            Map<String, Object> map
    ) {
        Iterable<Message> messages = messageRepository.findAll();
        //messages = messageRepository.findByTopic(filter);
        map.put("messages", messages);
        return "topic";
    }

    @PostMapping("/topic")
    public String readFromTopic(
            @AuthenticationPrincipal User user,
            @RequestParam (required = false)String filter,
            @RequestParam String text,
            Map<String, Object> map
    ){
        Message msg = new Message();
        msg.setTextMessage(text);
        msg.setAuthor(user);
        msg.setTopic(topicRepository.findByThemeName(filter));
        messageRepository.save(msg);
        Iterable<Message> messages = messageRepository.findAll();
        map.put("messages", messages);
        return "topic";
    }
}
