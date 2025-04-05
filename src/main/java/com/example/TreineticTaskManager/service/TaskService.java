package com.example.TreineticTaskManager.service;

import com.example.TreineticTaskManager.dao.TaskDAO;
import com.example.TreineticTaskManager.dao.UserDAO;
import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import com.example.TreineticTaskManager.exceptions.DataPersistException;
import com.example.TreineticTaskManager.exceptions.TaskNotFoundException;
import com.example.TreineticTaskManager.exceptions.UserNotFoundException;
import com.example.TreineticTaskManager.util.MappingObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MappingObjects mappingObjects;

    public List<TaskDTO> getAllTasks(Long user_id) {
        UserEntity user = findUserOrThrow(user_id);
        List<TaskEntity> byUser = taskDAO.findByUser(user);
        return mappingObjects.taskEntityListToDTOList(byUser);
    }

    public TaskDTO getTaskById(Long task_id) {
        TaskEntity task = findTaskOrThrow(task_id);
        return mappingObjects.taskEntityToDTO(task);
    }

    public TaskDTO saveTask(TaskDTO taskDTO) {
        UserEntity userEntity = findUserOrThrow(taskDTO.getUser_id());
        TaskEntity taskToSave = new TaskEntity(taskDTO.getId(), taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getCreatedAt(), userEntity);
        TaskEntity savedTask = taskDAO.save(taskToSave);
        return mappingObjects.taskEntityToDTO(savedTask);
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        TaskEntity existingTask = findTaskOrThrow(taskDTO.getId());
        UserEntity userEntity = findUserOrThrow(taskDTO.getUser_id());
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setCreatedAt(taskDTO.getCreatedAt());
        existingTask.setUser(userEntity);
        TaskEntity updatedTask = taskDAO.save(existingTask);
        return mappingObjects.taskEntityToDTO(updatedTask);
    }

    public void deleteTaskById(Long taskId) {
        TaskEntity existingTask = findTaskOrThrow(taskId);
        taskDAO.delete(existingTask);
    }

    private UserEntity findUserOrThrow(Long userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    private TaskEntity findTaskOrThrow(Long taskId) {
        return taskDAO.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
    }


}
