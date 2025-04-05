package com.example.TreineticTaskManager.entity.impl;

import com.example.TreineticTaskManager.entity.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity implements SuperEntity {

    @Id
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
