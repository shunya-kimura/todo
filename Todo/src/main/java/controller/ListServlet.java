package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import dao.TaskDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		int user_id = (int)session1.getAttribute("id"); 
    	
        String sort = request.getParameter("sort");

        TaskDAO taskDAO = new TaskDAO();
        List<HashMap<String, String>> tasks = taskDAO.getTasksBySort(sort ,user_id);
        
        request.setAttribute("rows", tasks);
        
        String view = "/WEB-INF/views/list.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
