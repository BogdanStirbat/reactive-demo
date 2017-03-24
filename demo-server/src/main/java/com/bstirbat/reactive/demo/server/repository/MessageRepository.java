package com.bstirbat.reactive.demo.server.repository;


import com.bstirbat.reactive.demo.server.model.Message;

public interface MessageRepository {

    Message find (long id);
}
