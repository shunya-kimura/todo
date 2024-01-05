package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.TodoDAO;
import model.dto.TodoDTO;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException {
		
		int postId = Integer.parseInt(request.getParameter("id"));
		TodoDAO todoDAO = new TodoDAO();
		TodoDTO todoDTO = new TodoDTO();
		
		try {
			todoDTO = todoDAO.showTodo(postId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("id", todoDTO.getId());
		request.setAttribute("title", todoDTO.getTitle());
		request.setAttribute("content", todoDTO.getContent());
		request.setAttribute("ymd", todoDTO.getYmd());
		
		String view = "/WEB-INF/views/edit.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}