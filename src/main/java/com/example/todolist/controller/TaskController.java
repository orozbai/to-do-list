package com.example.todolist.controller;

import com.example.todolist.dto.TaskCreateDTO;
import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.entity.Task;
import com.example.todolist.security.SecurityConfig;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    @GetMapping("/{id}")
    public Task taskDetails(@PathVariable Long id) {
        return taskService.taskDetailed(id, SecurityConfig.getCurrentUserId());
    }

    @PostMapping("/update/{id}")
    public String createTask(@PathVariable Long id) {
        return taskService.updateTask(id, SecurityConfig.getCurrentUserId());
    }
}
