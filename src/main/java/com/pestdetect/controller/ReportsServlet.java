package com.pestdetect.controller;

import com.pestdetect.dao.DetectionDAO;
import com.pestdetect.dao.FarmerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** Builds the numbers shown on the admin Reports page. */
@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            DetectionDAO dd = new DetectionDAO();
            req.setAttribute("totalFarmers", new FarmerDAO().count());
            req.setAttribute("totalDetections", dd.count());
            req.setAttribute("todayCount", dd.countToday());
            req.setAttribute("monthCount", dd.countThisMonth());
            req.setAttribute("mostCommon", dd.mostCommon());
            req.setAttribute("allDetections", dd.findAll());
            req.getRequestDispatcher("/admin/reports.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
