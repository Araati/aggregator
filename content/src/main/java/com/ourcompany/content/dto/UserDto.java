package com.ourcompany.content.dto;

import com.ourcompany.content.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.User;
import role.Role;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements User {

    private Long id;

    private String userName;

    private Role role;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public UserDto(UserEntity user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.enabled = user.isEnabled();
    }
}
