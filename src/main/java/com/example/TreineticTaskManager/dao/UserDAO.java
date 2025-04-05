package com.example.TreineticTaskManager.dao;

import com.example.TreineticTaskManager.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity,Long> {
}
