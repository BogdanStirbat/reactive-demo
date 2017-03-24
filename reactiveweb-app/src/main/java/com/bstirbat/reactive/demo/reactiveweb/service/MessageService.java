package com.bstirbat.reactive.demo.reactiveweb.service;

import com.bstirbat.reactive.demo.reactiveweb.model.Message;
import reactor.core.publisher.Mono;

public interface MessageService {

    Mono<Message> loadMessage(int messageId);
}
