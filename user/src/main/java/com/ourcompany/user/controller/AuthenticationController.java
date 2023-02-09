package com.ourcompany.user.controller;


import com.ourcompany.content.dto.UserCreateDto;
import com.ourcompany.content.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserCreateDto userCreateDto){
        authenticationFacade.registration(userCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserCreateDto userCreateDto){
        return authenticationFacade.login(userCreateDto);
    }
}
