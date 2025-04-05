package com.example.TreineticTaskManager.util;

import com.example.TreineticTaskManager.dto.impl.TaskDTO;
import com.example.TreineticTaskManager.dto.impl.UserDTO;
import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingObjects {

    @Autowired
    private ModelMapper modelMapper;

    public TaskDTO taskEntityToDTO(TaskEntity taskEntity){
        return modelMapper.map(taskEntity, TaskDTO.class);
    }
    public List<TaskDTO> taskEntityListToDTOList(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .map(this::taskEntityToDTO)
                .toList();
    }
    public UserEntity toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
