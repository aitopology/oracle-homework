package com.example.web;

import com.example.dao.UserDAO;
import com.example.entity.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


public class AddUserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
            request.getParameter("username"),
            request.getParameter("password")
        );

        try {
            userDAO.addUser(user);
            response.sendRedirect(request.getContextPath() + "/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}