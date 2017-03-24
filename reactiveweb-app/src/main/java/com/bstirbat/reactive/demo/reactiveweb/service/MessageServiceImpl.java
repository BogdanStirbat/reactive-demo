package com.bstirbat.reactive.demo.reactiveweb.service;


import com.bstirbat.reactive.demo.reactiveweb.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private final WebClient webClient;

    public MessageServiceImpl() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    @Override
    public Mono<Message> loadMessage(int messageId) {
        return this.webClient
                .get()
                .uri("/message/{messageId}", messageId)
                .exchange()
                .then(response -> response.bodyToMono(Message.class));
    }
}
