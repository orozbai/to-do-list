package com.example.todolist.util;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.dao.UserDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    @Bean
    CommandLineRunner init(UserDAO userDAO, TaskDAO taskDAO) {
        return (args) -> {
            userDAO.createTable();
            taskDAO.createTable();
        };
    }
}
