package com.pestdetect.controller;

import com.pestdetect.dao.DetectionDAO;
import com.pestdetect.dao.PestDAO;
import com.pestdetect.model.Detection;
import com.pestdetect.model.Pest;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

/**
 * Receives the uploaded image + the prediction made in the browser by
 * Teachable Machine (pestName + confidence), saves the image, looks up the
 * matching pest details, stores a detection_history row, then shows result.jsp.
 */
@WebServlet("/farmer/detect")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)   // 5 MB limit
public class DetectionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int farmerId = (int) req.getSession().getAttribute("farmerId");
        String pestName = req.getParameter("pestName");
        String confStr  = req.getParameter("confidence");

        // ---- validate ----
        if (pestName == null || confStr == null) {
            resp.sendRedirect(req.getContextPath() + "/farmer/upload.jsp?error=nopredict");
            return;
        }
        double confidence;
        try { confidence = Double.parseDouble(confStr); }
        catch (NumberFormatException e) { confidence = 0; }

        // ---- save uploaded image into /uploads ----
        String savedPath = null;
        Part part = req.getPart("image");
        if (part != null && part.getSize() > 0) {
            String ext = "";
            String submitted = part.getSubmittedFileName();
            if (submitted != null && submitted.contains(".")) {
                ext = submitted.substring(submitted.lastIndexOf('.'));
            }
            String fileName = "img_" + System.currentTimeMillis() + ext;
            String uploadDir = getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            part.write(uploadDir + File.separator + fileName);
            savedPath = "uploads/" + fileName;   // relative URL used in JSP <img>
        }

        try {
            // ---- look up pest details from the knowledge base ----
            Pest pest = new PestDAO().findByName(pestName);

            // ---- log this detection ----
            Detection d = new Detection();
            d.setFarmerId(farmerId);
            d.setPestId(pest == null ? null : pest.getId());
            d.setPestName(pestName);
            d.setConfidence(confidence);
            d.setImagePath(savedPath);
            new DetectionDAO().save(d);

            // ---- pass everything to result.jsp ----
            req.setAttribute("pest", pest);
            req.setAttribute("detection", d);
            req.getRequestDispatcher("/farmer/result.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Detection failed", e);
        }
    }
}
