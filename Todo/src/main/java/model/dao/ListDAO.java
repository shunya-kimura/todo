package model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.dto.TodoDTO;


public class ListDAO implements Serializable {
    

    public List<TodoDTO> ListTodo(int userId) 
    		throws SQLException, ClassNotFoundException {
    	
        List<TodoDTO> lists = new ArrayList<>();
        
        String sql = "SELECT * FROM posts WHERE user_id = ?";
       
        try (Connection con = DBConnection.getConnection()) {
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setInt(1, userId);
             ResultSet res = statement.executeQuery();
        	
            while (res.next()) {
                int id = res.getInt("id");
                String title = res.getString("title");
                String content = res.getString("content");
                Date ymd = res.getDate("ymd");
                String priority = res.getString("priority");
                int user_id = res.getInt("user_id");
               
                TodoDTO list = new TodoDTO(id, title, content, ymd, priority, user_id);
                lists.add(list);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }
}
