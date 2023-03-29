package com.example.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String header;
    private String description;
    private Date taskDate;
    private Integer whoseTask;
    private String status;
}
