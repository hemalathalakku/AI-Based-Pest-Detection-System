package com.pestdetect.controller;

import com.pestdetect.dao.PestDAO;
import com.pestdetect.model.Pest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/pests")
public class ManagePestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("pests", new PestDAO().findAll());
            req.getRequestDispatcher("/admin/manage-pests.jsp").forward(req, resp);
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            PestDAO dao = new PestDAO();
            if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")));
            } else { // add
                Pest p = new Pest();
                p.setPestName(req.getParameter("pestName"));
                p.setCropName(req.getParameter("cropName"));
                p.setSymptoms(req.getParameter("symptoms"));
                p.setCauses(req.getParameter("causes"));
                p.setTreatment(req.getParameter("treatment"));
                p.setPrevention(req.getParameter("prevention"));
                dao.add(p);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/pests");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
