package com.ourcompany.content.dao;

import com.ourcompany.content.model.entity.UserEntity;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getUserById(Long id);

    UserEntity getUserByUserName(String userName);
}
