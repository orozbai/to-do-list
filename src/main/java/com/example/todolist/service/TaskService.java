package com.example.todolist.service;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.dto.TaskCreateDTO;
import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;
    public String updateTask(Long taskId, String status) {
        return taskDAO.updateTask(taskId, status);
    }
    public Task taskDetailed(Long taskId) {
        return taskDAO.taskDetailed(taskId);
    }
    public List<TaskListDTO> showTaskList(Long userId) {
        return taskDAO.showTaskList(userId);
    }

    public TaskCreateDTO createTask(TaskCreateDTO taskCreateDTO, Long userId) {
        var task = Task.builder()
                .header(taskCreateDTO.getHeader())
                .description(taskCreateDTO.getDescription())
                .taskDate(taskCreateDTO.getTaskDate())
                .build();
        taskDAO.createTask(task, userId);
        return TaskCreateDTO.from(task);
    }
}
