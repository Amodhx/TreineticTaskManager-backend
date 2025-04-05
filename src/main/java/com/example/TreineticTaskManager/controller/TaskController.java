package com.example.TreineticTaskManager.controller;


import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.exceptions.TaskNotFoundException;
import com.example.TreineticTaskManager.exceptions.UserNotFoundException;
import com.example.TreineticTaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @GetMapping(path = "getAllTasks/{user_id}")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable("user_id") Long user_id){
        try {
            return new ResponseEntity<>(taskService.getAllTasks(user_id),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "getTaskById/{task_id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("task_id") Long task_id){
        try {
            return new ResponseEntity<>(taskService.getTaskById(task_id),HttpStatus.CREATED);
        }catch (TaskNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping(path = "/deleteTask/{task_id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable("task_id") Long task_id){
        try {
            taskService.deleteTaskById(task_id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (TaskNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @PostMapping(path = "/saveTask")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDTO){
        try {
            TaskDTO savedTask = taskService.saveTask(taskDTO);
            return new ResponseEntity<>(savedTask,HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping(path = "/updateTask")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO){
        try {
            TaskDTO updatedTask = taskService.updateTask(taskDTO);
            return new ResponseEntity<>(updatedTask,HttpStatus.NO_CONTENT);
        }catch (TaskNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
