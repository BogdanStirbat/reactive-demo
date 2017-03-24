package com.bstirbat.reactive.demo.server.controller;

import com.bstirbat.reactive.demo.server.model.Message;
import com.bstirbat.reactive.demo.server.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message/{id}")
    @ResponseBody
    public Message getMessage(@PathVariable String id) {
        return messageRepository.find(Integer.parseInt(id));
    }
}
