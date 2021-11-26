package com.springbank.user.query.api.handlers;

import com.springbank.user.core.events.UserRegistredEvent;
import com.springbank.user.core.events.UserRemovedEvent;
import com.springbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegistredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
