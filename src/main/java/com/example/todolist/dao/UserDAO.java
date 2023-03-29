package com.example.todolist.dao;

import com.example.todolist.dto.RegisterDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends BaseDAO {
    public UserDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public String getEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE '" + email + "'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public boolean existsByEmail(String email) {
        return getEmail(email).contains(email);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "username TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL);" +
                "INSERT INTO users (username, email, password) \n" +
                "VALUES \n" +
                "('admin', 'admin@gmail.com', 'admin')," +
                "('guest', 'guest@gmail.com', 'guest');");
    }

    public void registerUser(RegisterDTO registerDTO) {
        jdbcTemplate.execute("INSERT INTO users (username, email, password) \n" +
                "VALUES \n" +
                "('" + registerDTO.getUsername() + "', '" + registerDTO.getEmail() + "'," +
                " '" + registerDTO.getPassword() + "');");
    }
}
