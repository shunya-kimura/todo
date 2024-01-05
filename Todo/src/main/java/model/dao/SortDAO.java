package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.dto.TodoDTO;

public class SortDAO {
	
	 public List<TodoDTO> ListTodo(int userId, String sort) 
			throws SQLException, ClassNotFoundException {
		
		List<TodoDTO> sortLists = new ArrayList<>();
		
		String sql = "SELECT * FROM posts WHERE user_id = ?";
		if ("arc".equals("sort")) {
			sql += "ORDER BY ymd ASC";
		} else if ("desc".equals("sort")) {
			sql += "ORDER BY ymd DESC";
		} else if ("priority_acs".equals("sort")) {
			sql += "ORDER BY CASE priority WHEN 'high' THEN 1 WHEN 'normal' THEN 2 WHEN 'low' THEN 3 ELSE 4 END, ymd";
		} else if ("priority_desc".equals("sort")) {
			sql += "ORDER BY CASE priority WHEN 'low' THEN 1 WHEN 'normal' THEN 2 WHEN 'high' THEN 3 ELSE 4 END, ymd";
		}
		
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,  userId);
			ResultSet res = statement.executeQuery();
			
			while (res.next()) {
				int id = res.getInt("id");
				String title = res.getString("title");
				String content = res.getString("content");
				Date ymd = res.getDate("ymd");
				String priority = res.getString("priority");
				int user_id = res.getInt("user_id");
				
				TodoDTO sortList = new TodoDTO(id, title, content, ymd, priority, user_id);
				sortLists.add(sortList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortLists;
	}
	
}