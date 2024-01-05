package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.UserDTO;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
        String view = "/WEB-INF/views/index.jsp";
        req.getRequestDispatcher(view).forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserDAO userDAO = new UserDAO();
        try {
            UserDTO user = userDAO.getUser(username, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("id", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("profile", user.getProfile());
                res.sendRedirect("welcome");
            } else {
                String view = "/WEB-INF/views/index.jsp";
                req.getRequestDispatcher(view).forward(req, res);
            }
        } catch (SQLException | NoSuchAlgorithmException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Database Connection Failed", e);
        }
      }
    }
