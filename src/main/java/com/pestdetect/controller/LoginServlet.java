package com.pestdetect.controller;

import com.pestdetect.dao.FarmerDAO;
import com.pestdetect.model.Farmer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** Farmer login. On success stores farmerId + farmerName in the session. */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Farmer f = new FarmerDAO().login(email, password);
            if (f != null) {
                HttpSession session = req.getSession();
                session.setAttribute("farmerId", f.getId());
                session.setAttribute("farmerName", f.getName());
                resp.sendRedirect("farmer/dashboard.jsp");
            } else {
                resp.sendRedirect("login.jsp?error=1");
            }
        } catch (Exception e) {
            throw new ServletException("Login failed", e);
        }
    }
}
