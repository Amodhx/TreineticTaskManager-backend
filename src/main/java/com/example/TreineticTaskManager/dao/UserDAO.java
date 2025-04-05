package com.example.TreineticTaskManager.dao;

import com.example.TreineticTaskManager.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String userName);
}
