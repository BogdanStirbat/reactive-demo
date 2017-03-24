package com.bstirbat.reactive.demo.server.repository;

import com.bstirbat.reactive.demo.server.model.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Message find(long id) {
        List<Message> messages = this.entityManager.createQuery("select m from Message m where m.id=:id", Message.class)
                .setParameter("id", id)
                .getResultList();

        if (messages == null || messages.isEmpty()) {
            return null;
        }

        return messages.get(0);
    }
}
