package com.example.todolist.controller;

import com.example.todolist.dto.TaskCreateDTO;
import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.security.SecurityConfig;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public List<TaskListDTO> showTaskList() {

        return taskService.showTaskList(SecurityConfig.getCurrentUserId());
    }

    @PostMapping("/create")
    public TaskCreateDTO createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        return taskService.createTask(taskCreateDTO, SecurityConfig.getCurrentUserId());
    }
}
