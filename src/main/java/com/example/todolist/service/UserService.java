package com.example.todolist.service;

import com.example.todolist.dao.UserDAO;
import com.example.todolist.dto.RegisterDTO;
import com.example.todolist.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    public void registerUser(RegisterDTO registerDTO) {
        userDAO.registerUser(registerDTO);
    }

    public List<User> getByEmail(String email){
        return userDAO.getByEmail(email);
    }
    public User getById(Long id){
        return userDAO.getById(id);
    }
}
