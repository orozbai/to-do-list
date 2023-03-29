package com.example.todolist.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private Integer id;
    private String header;
    private String description;
    private Date toDoDate;
    private Integer whoseTask;
    private String status;
}
