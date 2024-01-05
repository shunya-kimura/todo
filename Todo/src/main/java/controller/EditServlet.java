package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBConnection;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {
		
		int postId = Integer.parseInt(request.getParameter("id"));
		
		String sql = "SELECT * FROM posts WHERE id = ?";
		try (Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, postId);
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				
				String id = results.getString("id");
				request.setAttribute("id" ,id);
				
				String title = results.getString("title");
				request.setAttribute("title" ,title);
				
				String content = results.getString("content").
				replaceAll("¥n", "<br>");
			    request.setAttribute("content" ,content);
				
			}
		} catch (Exception e) {
			request.setAttribute("message", "Exception:" + e.
			getMessage());
		}
		
		String view = "/WEB-INF/views/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher
		(view);
		dispatcher.forward(request, response);
	}
}