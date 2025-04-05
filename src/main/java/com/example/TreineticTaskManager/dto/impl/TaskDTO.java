package com.example.TreineticTaskManager.dto.impl;

import com.example.TreineticTaskManager.dto.SuperDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
