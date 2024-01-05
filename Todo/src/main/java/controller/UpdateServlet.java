package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBConnection;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {

		int postId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String sql = "UPDATE posts SET title = ?, content = ? WHERE id =?";
		try (Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, title);
			statement.setString(2, content);
			statement.setInt(3, postId);
			int number = statement.executeUpdate();
			request.setAttribute("message", "ID" + postId + 
			"の更新ができました");
		
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.
			getMessage());
		}
		
		String forward = "/show?id=" + postId;
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
	}
}