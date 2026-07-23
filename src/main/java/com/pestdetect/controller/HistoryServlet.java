package com.pestdetect.controller;

import com.pestdetect.dao.DetectionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/farmer/history")
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int farmerId = (int) req.getSession().getAttribute("farmerId");
        try {
            req.setAttribute("list", new DetectionDAO().findByFarmer(farmerId));
            req.getRequestDispatcher("/farmer/history.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
