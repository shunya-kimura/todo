package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ListDAO;

@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {
		
		int postId = Integer.parseInt(request.getParameter("id"));
		
		ListDAO listDAO = new ListDAO();
		try {
			listDAO.destroyTodo(postId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String forward = "/list";
		RequestDispatcher dispatcher = request.getRequestDispatcher
		(forward);
		dispatcher.forward(request, response);
	}
}