package com.bstirbat.reactive.demo.servlet.service;


import com.bstirbat.reactive.demo.servlet.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String MESSAGE_URI = "http://localhost:8080/message/{id}";

    private final RestTemplate restTemplate;

    public MessageServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public Message loadMessage(int messageId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(messageId));

        return restTemplate.getForObject(MESSAGE_URI, Message.class, params);
    }
}
