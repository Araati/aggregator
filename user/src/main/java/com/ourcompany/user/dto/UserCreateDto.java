package com.ourcompany.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @JsonProperty(value = "username", required = true)
    String username;

    @JsonProperty(value = "password", required = true)
    String password;
}
