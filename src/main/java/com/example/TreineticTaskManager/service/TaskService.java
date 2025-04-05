package com.example.TreineticTaskManager.service;

import com.example.TreineticTaskManager.dao.TaskDAO;
import com.example.TreineticTaskManager.dao.UserDAO;
import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDAO taskDAO;
    private final UserDAO userDAO;

    public List<TaskDTO> getAllTasks(Long user_id){
        UserEntity user = userDAO.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<TaskDTO> list = new ArrayList<>();
        List<TaskEntity> byUser = taskDAO.findByUser(user);

        for (TaskEntity taskEntity : byUser) {
            list.add(new TaskDTO(
                    taskEntity.getId(),
                    taskEntity.getTitle(),
                    taskEntity.getDescription(),
                    taskEntity.getStatus(),
                    taskEntity.getCreatedAt(),
                    taskEntity.getUser().getId()
            ));
        }
        return  list;
    }
    public TaskDTO getTaskById(Long task_id){
        Optional<TaskEntity> task = taskDAO.findById(task_id);
        if (task.isPresent()){
            return new TaskDTO(task.get().getId(),
                    task.get().getTitle(),
                    task.get().getDescription(),
                    task.get().getStatus(),
                    task.get().getCreatedAt(),
                    task.get().getUser().getId());
        }else {
            throw  new RuntimeException("Cant find task data");
        }
    }
    public TaskDTO saveTask(TaskDTO taskDTO){
        UserEntity userEntity = userDAO.findById(taskDTO.getUser_id()).orElseThrow(()->
                new RuntimeException("Invalid user id"));
        TaskEntity taskToSave = new TaskEntity(taskDTO.getId(),taskDTO.getTitle(),taskDTO.getDescription(),taskDTO.getStatus(),taskDTO.getCreatedAt(),userEntity);
        TaskEntity savedTask = taskDAO.save(taskToSave);

        return new TaskDTO(savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt(),
                savedTask.getUser().getId());
    }
    public TaskDTO updateTask(TaskDTO taskDTO) {
        TaskEntity existingTask = taskDAO.findById(taskDTO.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskDTO.getId()));

        UserEntity userEntity = userDAO.findById(taskDTO.getUser_id())
                .orElseThrow(() -> new RuntimeException("Invalid user id: " + taskDTO.getUser_id()));

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setCreatedAt(taskDTO.getCreatedAt());
        existingTask.setUser(userEntity);

        TaskEntity updatedTask = taskDAO.save(existingTask);

        return new TaskDTO(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getStatus(),
                updatedTask.getCreatedAt(),
                updatedTask.getUser().getId()
        );
    }
    public void deleteTaskById(Long taskId) {
        TaskEntity existingTask = taskDAO.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        taskDAO.delete(existingTask);
    }

}
