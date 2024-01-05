package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ShowDAO;
import model.dto.TodoDTO;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException,
	IOException {
		
		int postId = Integer.parseInt(request.getParameter("id"));
		ShowDAO post = new ShowDAO();
		TodoDTO todoDTO = null;
		try {
			todoDTO = post.showTodo(postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		if (post != null) {
			request.setAttribute("id", todoDTO.getId());
			request.setAttribute("title", todoDTO.getTitle());
			request.setAttribute("content", todoDTO.getContent());
			request.setAttribute("ymd", todoDTO.getYmd());
		}
		
		String view = "/WEB-INF/views/post.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}