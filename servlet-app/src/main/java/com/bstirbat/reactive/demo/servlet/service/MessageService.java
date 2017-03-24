package com.bstirbat.reactive.demo.servlet.service;


import com.bstirbat.reactive.demo.servlet.model.Message;

public interface MessageService {

    Message loadMessage(int messageId);
}
