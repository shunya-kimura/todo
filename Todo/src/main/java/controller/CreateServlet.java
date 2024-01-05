package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.TodoDAO;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {
		
		HttpSession session = request.getSession(false);
    	Object userId = session != null ? session.getAttribute("id") : null;
    	if (userId == null) {
    		response.sendRedirect("login");
    		return;
    	}
		
		HttpSession session1 = request.getSession();
		int user_id = (int)session1.getAttribute("id"); 

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ymd = request.getParameter("ymd");
		String priority = request.getParameter("priority");
		
		TodoDAO todoDAO = new TodoDAO();
		try {
			todoDAO.createTodo(title, content, ymd, priority, user_id);
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