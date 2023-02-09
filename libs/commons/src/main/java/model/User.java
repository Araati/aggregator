package model;

import role.Role;

import java.time.LocalDateTime;

public interface User {

    Long getId();

    String getUserName();

    Role getRole();

    boolean isEnabled();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
