package com.springbank.user.query.api.handlers;

import com.springbank.user.core.events.UserRegistredEvent;
import com.springbank.user.core.events.UserRemovedEvent;
import com.springbank.user.core.events.UserUpdatedEvent;
import com.springbank.user.query.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup(value = "user-group")
@AllArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository repository;

    @EventHandler
    @Override
    public void on(UserRegistredEvent event) {
        repository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        repository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        repository.deleteById(event.getId());
    }
}
