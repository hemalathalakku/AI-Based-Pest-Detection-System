package com.pestdetect.controller;

import com.pestdetect.dao.FarmerDAO;
import com.pestdetect.model.Farmer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** GET => load current farmer and show the form. POST => save changes. */
@WebServlet("/farmer/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("farmerId");
        try {
            req.setAttribute("farmer", new FarmerDAO().findById(id));
            req.getRequestDispatcher("/farmer/profile.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("farmerId");
        Farmer f = new Farmer();
        f.setId(id);
        f.setName(req.getParameter("name"));
        f.setPhone(req.getParameter("phone"));
        f.setAddress(req.getParameter("address"));
        try {
            new FarmerDAO().updateProfile(f);
            req.getSession().setAttribute("farmerName", f.getName());
            resp.sendRedirect(req.getContextPath() + "/farmer/profile?updated=1");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
