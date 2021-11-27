package com.springbank.user.core.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @NotBlank(message = "firstname is mandatory")
    private String firstname;
    @NotBlank(message = "lastname is mandatory")
    private String lastname;
    @Email(message = "please provide a valid email address")
    private String email;
    @NotNull(message = "please provide account credentials")
    @Valid
    private Account account;
}
