package com.springbank.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
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
    private Account account;
}
