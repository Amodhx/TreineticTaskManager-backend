package com.example.TreineticTaskManager.controller;


import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
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
    public List<TaskDTO> getAllTasks(@PathVariable("user_id") Long user_id){
        return taskService.getAllTasks(user_id);
    }

    @GetMapping(path = "getTaskById/{task_id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("task_id") Long task_id){
        return new ResponseEntity<>(taskService.getTaskById(task_id),HttpStatus.OK);
    }
    @DeleteMapping(path = "/deleteTask/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("task_id") Long task_id){
        taskService.deleteTaskById(task_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(path = "/saveTask")
    public ResponseEntity<Void> saveTask(@RequestBody TaskDTO taskDTO){
        taskService.saveTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping(path = "/updateTask")
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO){
        taskService.updateTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
