package com.example.todolist.dto;

import com.example.todolist.entity.Task;
import lombok.*;

import java.sql.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TaskCreateDTO {
    private String header;
    private String description;
    private Date taskDate;

    public static TaskCreateDTO from(Task task) {
        return builder()
                .header(task.getHeader())
                .description(task.getDescription())
                .taskDate(task.getTaskDate())
                .build();
    }
}
