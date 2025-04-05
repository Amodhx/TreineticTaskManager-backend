package com.example.TreineticTaskManager.util;

import com.example.TreineticTaskManager.dao.TaskDAO;
import com.example.TreineticTaskManager.dao.UserDAO;
import com.example.TreineticTaskManager.entity.impl.TaskEntity;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenerateId {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TaskDAO taskDAO;
    public Long generateUserId(){
        List<UserEntity> all = userDAO.findAll();
        return  all.get(all.size() - 1).getId() + 1;
    }
    public Long generateTaskId(){
        List<TaskEntity> all = taskDAO.findAll();
        return all.get(all.size() - 1).getId() + 1;
    }
}
