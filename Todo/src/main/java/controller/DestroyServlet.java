package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.TodoDAO;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException {
		int postId = Integer.parseInt(request.getParameter("id"));
		TodoDAO todoDAO = new TodoDAO();
		try {
			todoDAO.destroyTodo(postId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("list");
	}
}