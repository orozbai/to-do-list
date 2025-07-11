package com.example.todolist.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskListDTO {
    private Integer id;
    private String header;
    private String status;
    private Date taskDate;
}
