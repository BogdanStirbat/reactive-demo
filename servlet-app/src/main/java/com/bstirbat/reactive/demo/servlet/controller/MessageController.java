package com.bstirbat.reactive.demo.servlet.controller;

import com.bstirbat.reactive.demo.servlet.model.Message;
import com.bstirbat.reactive.demo.servlet.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/{id}")
    @ResponseBody
    public Message loadMessage(@PathVariable String id) {
        return messageService.loadMessage(Integer.parseInt(id));
    }
}
