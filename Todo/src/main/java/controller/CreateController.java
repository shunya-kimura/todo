package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBConnection;
import utils.HashGenerator;

@WebServlet("/createuser")
public class CreateController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String view = "/WEB-INF/views/create.jsp";
        req.getRequestDispatcher(view).forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String profile = req.getParameter("profile");
        
        try (Connection conn = DBConnection.getConnection()) {
            // 5.パスワードをハッシュ化する
            String hashedPassword = HashGenerator.generateHash(password);
            String sql = "INSERT INTO users (username, password, profile) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                // 6.ハッシュ化した値を利用
                stmt.setString(2, hashedPassword);
                stmt.setString(3, profile);
                stmt.execute();

                String view = "/WEB-INF/views/index.jsp";
                req.getRequestDispatcher(view).forward(req, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database Connection Failed", e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ServletException("Generate hash Failed", e);
        } catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
    }
}
