package com.example.todolist.service;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.dto.TaskListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;
    public List<TaskListDTO> showTaskList(Long userId) {
        return taskDAO.showTaskList(userId);
    }
}
