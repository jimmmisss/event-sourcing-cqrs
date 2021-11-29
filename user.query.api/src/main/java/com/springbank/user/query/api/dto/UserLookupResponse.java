package com.springbank.user.query.api.dto;

import com.springbank.user.core.dto.BaseResponse;
import com.springbank.user.core.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(String message) {
        super(message);
    }

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message, User users) {
        super(message);
        this.users = new ArrayList<>();
        this.users.add(users);
    }

    public UserLookupResponse(User users) {
        super(null);
        this.users = new ArrayList<>();
        this.users.add(users);
    }
}
