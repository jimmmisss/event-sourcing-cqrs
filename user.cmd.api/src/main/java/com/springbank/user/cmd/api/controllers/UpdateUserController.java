package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.cmd.api.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/user-command-updade")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable(value = "id") String id,
                                               @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new BaseResponse("User successfully updated!"), OK);
        } catch (Exception e) {
            var safeMessageError = "Error while processing update user request for id - " + id;
            log.info(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeMessageError), INTERNAL_SERVER_ERROR);
        }
    }
}
