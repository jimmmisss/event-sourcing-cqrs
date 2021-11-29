package com.springbank.user.query.api.handlers;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository repository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var users = repository.findById(query.getId());
        return users.map(UserLookupResponse::new).orElse(null);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        var users = new ArrayList<>(repository.findByFilterRegex(query.getFilter()));
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(repository.findAll());
        return new UserLookupResponse(users);
    }
}
