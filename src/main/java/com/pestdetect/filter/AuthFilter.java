package com.pestdetect.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Guards protected areas:
 *  - anything under /farmer/  needs a logged-in farmer (session "farmerId")
 *  - anything under /admin/   needs a logged-in admin  (session "adminUser")
 * Public pages (home, login, register) are untouched.
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String ctx = request.getContextPath();

        boolean farmerLoggedIn = session != null && session.getAttribute("farmerId") != null;
        boolean adminLoggedIn  = session != null && session.getAttribute("adminUser") != null;

        if (path.startsWith("/farmer/") && !farmerLoggedIn) {
            response.sendRedirect(ctx + "/login.jsp");
            return;
        }
        // Allow the admin login page itself (otherwise it would redirect to itself forever)
        if (path.equals("/admin/login.jsp")) { chain.doFilter(req, res); return; }

        if (path.startsWith("/admin/") && !adminLoggedIn) {
            response.sendRedirect(ctx + "/admin/login.jsp");
            return;
        }
        chain.doFilter(req, res);
    }
}
