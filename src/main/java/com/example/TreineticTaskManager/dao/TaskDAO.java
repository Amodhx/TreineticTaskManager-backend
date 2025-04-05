package com.example.TreineticTaskManager.dao;

import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findByUser(UserEntity user);
}
