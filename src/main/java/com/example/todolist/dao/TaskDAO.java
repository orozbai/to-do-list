package com.example.todolist.dao;

import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.entity.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class TaskDAO extends BaseDAO {
    public TaskDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tasks (" +
                "id SERIAL PRIMARY KEY," +
                "header TEXT NOT NULL," +
                "description TEXT," +
                "taskDate DATE," +
                "whoseTask INTEGER," +
                "status TEXT," +
                "FOREIGN KEY (whoseTask) REFERENCES users(id) ON DELETE CASCADE);" +
                "INSERT INTO tasks (header, description, taskDate, whoseTask, status) \n" +
                "VALUES \n" +
                "('header', 'description', '2023-04-25', '1', 'new')," +
                "('header', 'description', '2023-04-20', '1', 'working')," +
                "('header', 'description', '2023-02-20', '1', 'end')," +
                "('header', 'description', '2023-04-25', '2', 'new')," +
                "('header', 'description', '2023-04-20', '2', 'working')," +
                "('header', 'description', '2023-02-20', '2', 'end');");
    }

    public List<TaskListDTO> showTaskList(Long userId) {
        String sql = "SELECT * FROM tasks WHERE whoseTask LIKE '" + userId + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskListDTO.class));
    }

    public void createTask(Task task, int userId) {
        String sql = "INSERT INTO tasks (header, description, taskDate, whoseTask, status)" +
                "values (?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getHeader());
            ps.setString(2, task.getDescription());
            ps.setDate(3, task.getTaskDate());
            ps.setInt(4, userId);
            ps.setString(5, "new");
            return ps;
        });
    }
}
