package com.pestdetect.controller;

import com.pestdetect.dao.AdminDAO;
import com.pestdetect.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            Admin a = new AdminDAO().login(req.getParameter("username"),
                                           req.getParameter("password"));
            if (a != null) {
                req.getSession().setAttribute("adminUser", a.getUsername());
                resp.sendRedirect("admin/dashboard.jsp");
            } else {
                resp.sendRedirect("admin/login.jsp?error=1");
            }
        } catch (Exception e) {
            throw new ServletException("Admin login failed", e);
        }
    }
}
