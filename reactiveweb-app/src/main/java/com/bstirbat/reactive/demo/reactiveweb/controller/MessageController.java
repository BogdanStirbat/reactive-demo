package com.bstirbat.reactive.demo.reactiveweb.controller;

import com.bstirbat.reactive.demo.reactiveweb.model.Message;
import com.bstirbat.reactive.demo.reactiveweb.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/{id}")
    @ResponseBody
    public Mono<Message> loadMessage(@PathVariable String id) {
        Mono<Message> messageMono;

        LOGGER.info("Received request for message id: {}", id);
        try {
            messageMono = messageService.loadMessage(Integer.parseInt(id));
        } catch (Exception e) {
            LOGGER.error("An error occurred loading message id: {}", id);
            throw e;
        }
        LOGGER.info("Finished request for message id: {}", id);

        return messageMono;
    }
}
