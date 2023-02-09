package com.ourcompany.user.service;

import com.ourcompany.content.dao.UserRepository;
import com.ourcompany.content.dto.UserCreateDto;
import com.ourcompany.content.dto.UserDto;
import com.ourcompany.content.model.entity.UserEntity;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import model.Person;
import model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.getUserByUserName(username);
        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }
        return new Person(user);
    }

    public User getUserByUserName(String userName){
        return new UserDto(userRepository.getUserByUserName(userName));
    }

    public User createUser(final UserCreateDto userCreateDto){
        UserEntity user = new UserEntity();
        user.withUserName(user.getUserName())
                .withPassword(passwordEncoder.encode(userCreateDto.getPassword()))
                .withEnabled(false);
        userRepository.save(user);
        return new UserDto(user);
    }
}
