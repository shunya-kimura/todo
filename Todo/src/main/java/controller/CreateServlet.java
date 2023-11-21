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
import jakarta.servlet.http.HttpSession;

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
		
		
		String url = "jdbc:mysql://localhost/todo";
		String user = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO posts (title, content, ymd, priority, user_id) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection
		(url, user, password);
		PreparedStatement statement = connection.prepareStatement
		(sql)) {

			statement.setString(1, title);
			statement.setString(2, content);
			statement.setString(3, ymd);
			statement.setString(4, priority);
			statement.setInt(5, user_id);
			
			int number = statement.executeUpdate();
			request.setAttribute("message", "タイトル:" + title + 
			"の新規作成ができました");
		
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