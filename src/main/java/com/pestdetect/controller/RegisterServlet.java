package com.pestdetect.controller;

import com.pestdetect.dao.FarmerDAO;
import com.pestdetect.model.Farmer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** Handles farmer sign-up (form on register.jsp posts here). */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Farmer f = new Farmer();
        f.setName(req.getParameter("name"));
        f.setEmail(req.getParameter("email"));
        f.setPassword(req.getParameter("password"));
        f.setPhone(req.getParameter("phone"));
        f.setAddress(req.getParameter("address"));

        // Basic input validation
        if (f.getName() == null || f.getName().trim().isEmpty()
                || f.getEmail() == null || !f.getEmail().contains("@")
                || f.getPassword() == null || f.getPassword().length() < 4) {
            resp.sendRedirect("register.jsp?error=invalid");
            return;
        }

        try {
            boolean ok = new FarmerDAO().register(f);
            if (ok) resp.sendRedirect("login.jsp?registered=1");
            else    resp.sendRedirect("register.jsp?error=exists");   // email taken
        } catch (Exception e) {
            throw new ServletException("Registration failed", e);
        }
    }
}
