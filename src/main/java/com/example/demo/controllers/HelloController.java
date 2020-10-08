package com.example.demo.controllers;

import com.example.demo.domain.Message;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/greeting")
    public String greeting(Map<String, Object> map) {
        return "greeting";
    }
    /*@PostMapping("/greeting")
    public String greetPost(@RequestParam(required = false)String txt, Map<String, Object> map){
        map.put("txtovik", txt);
        return "greeting";
    }*/

    @GetMapping("/main")
    public String addTopic(Map<String, Object> map) {
        //Iterable<Message> messages = messageRepository.findAll();
        //map.put("messages", messages);

        Iterable<Topic> topics = topicRepository.findAll();
        map.put("topics", topics);
        return "main";
    }

    @PostMapping("/main")
    public String addTopic(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Map<String, Object> map
    ){

        Topic topic = new Topic();
        topic.setThemeName(text);
        topicRepository.save(topic);
        Iterable<Topic> topics = topicRepository.findAll();
        map.put("topics", topics);
        return "main";
        /*Message msg = new Message();
        msg.setAuthor(user);
        msg.setTextMessage(text);
        messageRepository.save(msg);*/

        /*Iterable<Message> messages = messageRepository.findAll();
        map.put("messages", messages);*/
    }
}
