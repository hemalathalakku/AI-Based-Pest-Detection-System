package com.pestdetect.controller;

import com.pestdetect.dao.FarmerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/farmers")
public class ManageFarmersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("farmers", new FarmerDAO().findAll());
            req.getRequestDispatcher("/admin/manage-farmers.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }

    // Delete a farmer
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            new FarmerDAO().delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/farmers");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
