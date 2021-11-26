package com.springbank.user.core.events;

import com.springbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistredEvent {

    private String id;
    private User user;
}