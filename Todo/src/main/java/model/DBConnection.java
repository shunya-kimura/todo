package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection()
			throws SQLException, ClassNotFoundException {
		
		final String URL = "jdbc:mysql://localhost/todo";
		final String USER = "root";
		final String PASS = "";
				 
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		return con;
	}
}