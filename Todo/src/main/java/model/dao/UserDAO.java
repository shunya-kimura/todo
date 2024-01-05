package model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.UserDTO;
import utils.HashGenerator;

public class UserDAO {
	
	public UserDTO getUser(String username, String password)
			throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		
		UserDTO user = null;
		
		String hashedPassword = HashGenerator.generateHash(password);
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, hashedPassword);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String profile = rs.getString("profile");
				
				user = new UserDTO();
				user.setId(id);
				user.setUsername(username);
				user.setProfile(profile);
			}
		}
		return user;
	}

}
