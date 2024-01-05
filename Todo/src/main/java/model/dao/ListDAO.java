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


public class ListDAO {
    
    public List<TodoDTO> ListTodo(int userId) throws ClassNotFoundException, SQLException {
        List<TodoDTO> lists = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection()) {
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setInt(1, userId);
             ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String title = res.getString("title");
                String content = res.getString("content");
                Date ymd = res.getDate("ymd");
                String priority = res.getString("priority");
                int user_id = res.getInt("user_id");
                
                lists.add(new TodoDTO(id, title, content, ymd, priority, user_id));
            }
        }
        return lists;
    }
    
    public TodoDTO editTodo(int postId) throws SQLException, ClassNotFoundException {
		TodoDTO post = new TodoDTO();
		String sql = "SELECT * FROM posts WHERE id = ?";
		
		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, postId);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				post.setId(res.getInt("id"));
				post.setTitle(res.getString("title"));
				post.setContent(res.getString("content").replaceAll("¥n", "<br>"));
				post.setYmd(res.getDate("ymd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return post;
	}
    
    public void destroyTodo(int postId) throws ClassNotFoundException, SQLException {
    	String sql = "DELETE FROM posts WHERE id = ?";
    	try(Connection con = DBConnection.getConnection();
    			PreparedStatement pstmt = con.prepareStatement(sql)) {
    				pstmt.setInt(1, postId);
    				pstmt.executeUpdate();
    			}
    }
    
}
