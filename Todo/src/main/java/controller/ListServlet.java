package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.ListDAO;
import model.dto.TodoDTO;


@WebServlet("/list")
public class ListServlet extends HttpServlet {   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	Object id = session != null ? session.getAttribute("id") : null;
    	
    	if (id == null) {
    		response.sendRedirect("login");
    		return;
    	}
    	
    	HttpSession session1 = request.getSession();
		int userId = (int)session1.getAttribute("id");
    	

        List<TodoDTO> todoList = new ArrayList<>();
        ListDAO dao = new ListDAO();
        
        try {
			todoList = dao.ListTodo(userId);
			request.setAttribute("todoList", todoList);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        
        String view = "/WEB-INF/views/list.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
