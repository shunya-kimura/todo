package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {
		
		if (request.getAttribute("message") == null) {
			request.setAttribute("message", "todoを管理しましょう");
		}

		int postId = Integer.parseInt(request.getParameter("id"));
		
		String url = "jdbc:mysql://localhost/todo";
		String user = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "DELETE  FROM posts WHERE id = ?";
		try (Connection connection = DriverManager.getConnection
		(url, user, password);
		PreparedStatement statement = connection.prepareStatement
		(sql)) {

			
			statement.setInt(1, postId);
			int number = statement.executeUpdate();
			request.setAttribute("message", "ID" + postId + 
			"の削除ができました");
		
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.
			getMessage());
		}
		
		String forward = "/list";
		RequestDispatcher dispatcher = request.getRequestDispatcher
		(forward);
		dispatcher.forward(request, response);
	}
}