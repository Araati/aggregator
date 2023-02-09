package com.ourcompany.content.facade;

import com.ourcompany.content.dto.UserCreateDto;
import com.ourcompany.content.service.UserService;
import exception.ResourceNotFoundException;
import jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final JwtTokenProvider jwtTokenProvider;


    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public void registration(UserCreateDto userCreateDto){
        User userDto = userService.getUserByUserName(userCreateDto.getUsername());
        if(userDto != null){
            //Кастомную ошубку
            throw new RuntimeException("User already exists");
        }
        userService.createUser(userCreateDto);
    }

    public Map<String, Object> login(UserCreateDto userCreateDto){
        try {
            User userDto = userService.getUserByUserName(userCreateDto.getUsername());
            if(userDto == null){
                //Кастомную ошубку
                throw new ResourceNotFoundException("User not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCreateDto.getUsername(), userCreateDto.getPassword()));
            Map<String, Object> data = new HashMap<>();
            data.put("id", userDto.getId());
            data.put("role", userDto.getRole());
            data.put("authentication", jwtTokenProvider.createToken(userDto.getUserName(), userDto.getRole()));
            return data;
        } catch (AuthenticationException e){
            throw new RuntimeException("Authorization failed");
        }
    }
}
