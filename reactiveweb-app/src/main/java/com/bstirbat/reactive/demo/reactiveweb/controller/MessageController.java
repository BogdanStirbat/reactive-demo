package com.bstirbat.reactive.demo.reactiveweb.controller;

import com.bstirbat.reactive.demo.reactiveweb.model.Message;
import com.bstirbat.reactive.demo.reactiveweb.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/{id}")
    @ResponseBody
    public Mono<Message> loadMessage(@PathVariable String id) {
        return messageService.loadMessage(Integer.parseInt(id));
    }
}
