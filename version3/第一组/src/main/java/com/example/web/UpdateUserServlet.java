package com.example.web;

import com.example.dao.UserDAO;
import com.example.entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet", value = "/update")
public class UpdateUserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String originalUsername = request.getParameter("originalUsername");
        String newUsername = request.getParameter("username");

        try {
            if(!originalUsername.equals(newUsername) && userDAO.checkUsernameExists(newUsername)) {
                request.setAttribute("error", "用户名已存在");
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String password = request.getParameter("password");
        User user = new User(newUsername, password);

        try {
            userDAO.updateUser(originalUsername, user);
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}