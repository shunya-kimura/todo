package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.TodoDTO;

public class ShowDAO {
	
	public TodoDTO showTodo(int postId) 
    		throws SQLException, ClassNotFoundException {
    	
        TodoDTO post = new TodoDTO();
        
        String sql = "SELECT * FROM posts WHERE id = ?";
       
        try (Connection con = DBConnection.getConnection()) {
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setInt(1, postId);
             ResultSet res = statement.executeQuery();
        	
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
 }
