package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.cmd.api.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/user-command-remove")
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public RemoveUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable(value = "id") String id) {
        try {
            commandGateway.sendAndWait(new RemoveUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("User successfully removed"), OK);
        } catch (Exception e) {
            var safeMessageError = "Error while processing delete user request for id - " + id;
            log.info(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeMessageError), INTERNAL_SERVER_ERROR);
        }
    }
}
