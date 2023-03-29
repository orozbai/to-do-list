package com.example.todolist.dao;

import com.example.todolist.dto.TaskListDTO;
import com.example.todolist.entity.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO extends BaseDAO {
    public TaskDAO(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public List<Task> taskDetailed(Long taskId, Long currentUserId) {
        try {
            String sql = "SELECT * FROM tasks WHERE id = '" + taskId + "'" +
                    "AND whosetask = '" + currentUserId + "'";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Task.class));
        } catch (Exception e) {
            List<Task> list = new ArrayList<>();
            list.add(new Task(666, "problem", e.getMessage(), null, 666, "bad"));
            return list;
        }

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
        String sql = "SELECT * FROM tasks WHERE whoseTask = '" + userId + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskListDTO.class));
    }

    public String updateTask(Long taskId, Long currentUserId) {
        try {
//            if (taskDetailed(taskId, currentUserId).get(0).getStatus().equals("new")) {
//                String sql = "UPDATE tasks SET status = ? WHERE id = ? and whosetask = ?";
//                jdbcTemplate.update(sql, "working", taskId, currentUserId);
//            } else if (taskDetailed(taskId, currentUserId).get(0).getStatus().equals("working")) {
//                String sql = "UPDATE tasks SET status = ? WHERE id = ? and whosetask = ?";
//                jdbcTemplate.update(sql, "end", taskId, currentUserId);
//            } else {
//                String sql = "UPDATE tasks SET status = ? WHERE id = ? and whosetask = ?";
//                jdbcTemplate.update(sql, "end", taskId, currentUserId);
//            }
            String sql = "UPDATE tasks SET status = ? WHERE id = ? and whosetask = ?";
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "working");
                ps.setLong(2, taskId);
                ps.setLong(3, currentUserId);
                return ps;
            });
            return "UPDATED";
        } catch (Exception e) {
            return "INCORRECT ID TASK";
        }
    }


    public void createTask(Task task, Long userId) {
        String sql = "INSERT INTO tasks (header, description, taskDate, whoseTask, status)" +
                "values (?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, task.getHeader());
            ps.setString(2, task.getDescription());
            ps.setDate(3, task.getTaskDate());
            ps.setLong(4, userId);
            ps.setString(5, "new");
            return ps;
        });
    }
}
