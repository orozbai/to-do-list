package com.example.todolist.dao;

import com.example.todolist.dto.RegisterDTO;
import com.example.todolist.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public User getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email LIKE '" + email + "'";
        return jdbcTemplate.queryForObject(sql, User.class);
    }
    public User getById(Long id) {
        String sql = "SELECT * FROM users WHERE id =" + id;
        return jdbcTemplate.queryForObject(sql, User.class);
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
                "password TEXT NOT NULL);");

        jdbcTemplate.execute("INSERT INTO users (username, email, password)\n" +
                "VALUES ('admin', 'admin@gmail.com', '"
                + new BCryptPasswordEncoder().encode("admin") + "')," +
                "('quest', 'quest@gmail.com', '"
                + new BCryptPasswordEncoder().encode("quest") + "');");
    }


    public void registerUser(RegisterDTO registerDTO) {
        jdbcTemplate.execute("INSERT INTO users (username, email, password) \n" +
                "VALUES \n" +
                "('" + registerDTO.getUsername() + "', '" + registerDTO.getEmail() + "'," +
                " '" + new BCryptPasswordEncoder().encode(registerDTO.getPassword()) + "');");
    }
}
