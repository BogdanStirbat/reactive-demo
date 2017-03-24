package com.bstirbat.reactive.demo.server.controller;

import com.bstirbat.reactive.demo.server.model.Message;
import com.bstirbat.reactive.demo.server.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message/{id}")
    @ResponseBody
    public Message getMessage(@PathVariable String id) {
        LOGGER.info("Received request for message id: {}", id);

        try {
            Thread.sleep(15 * 1000);
        } catch (InterruptedException e) {
            LOGGER.error("Error waiting 5 seconds", e);
        }

        LOGGER.info("Finished request for message id: {}", id);

        return messageRepository.find(Integer.parseInt(id));
    }
}
