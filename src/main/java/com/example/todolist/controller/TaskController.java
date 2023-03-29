package com.example.todolist.controller;

import com.example.todolist.dto.TaskCreateDTO;
import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/list/{userId}")
    public List<TaskListDTO> showTaskList(@PathVariable Long userId) {
        return taskService.showTaskList(userId);
    }

    @PostMapping("/create/{userId}")
    public TaskCreateDTO createTask(@RequestBody TaskCreateDTO taskCreateDTO, @PathVariable int userId) {
        return taskService.createTask(taskCreateDTO, userId);
    }
}
