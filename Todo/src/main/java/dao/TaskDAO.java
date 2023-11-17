package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskDAO {
    private final String url = "jdbc:mysql://localhost/todo";
    private final String user = "root";
    private final String password = "";

    public List<HashMap<String, String>> getTasksBySort(String sort) {
        List<HashMap<String, String>> tasks = new ArrayList<>();
        String sql = "SELECT * FROM posts";

        // ソートのためのSQL文を準備
        if ("asc".equals(sort)) {
            sql += " ORDER BY ymd ASC";
        } else if ("desc".equals(sort)) {
            sql += " ORDER BY ymd DESC";
        } else if ("priority_asc".equals(sort)) {
            sql += " ORDER BY CASE priority WHEN 'high' THEN 1 WHEN 'normal' THEN 2 WHEN 'low' THEN 3 ELSE 4 END, ymd";
        } else if ("priority_desc".equals(sort)) {
            sql += " ORDER BY CASE priority WHEN 'low' THEN 1 WHEN 'normal' THEN 2 WHEN 'high' THEN 3 ELSE 4 END, ymd";
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                HashMap<String, String> task = new HashMap<>();
                task.put("id", resultSet.getString("id"));
                task.put("title", resultSet.getString("title"));
                task.put("content", resultSet.getString("content"));
                task.put("ymd", resultSet.getString("ymd"));
                task.put("priority", resultSet.getString("priority"));
                tasks.add(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
