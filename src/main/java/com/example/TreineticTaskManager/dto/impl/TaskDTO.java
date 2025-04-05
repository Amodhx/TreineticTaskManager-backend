package com.example.TreineticTaskManager.dto.impl;

import com.example.TreineticTaskManager.dto.SuperDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskDTO implements SuperDTO {

    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String status;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private Long user_id;

    public TaskDTO(Long id, String title, String description, String status, LocalDateTime createdAt, Long user_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.user_id = user_id;
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
