package com.example.todolist.controller;

import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
