package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CalculateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("calculate")
public class IndexServlet extends HttpServlet {
    private CalculateService calculateService;
    @Override
    public void init() throws ServletException {
        calculateService = (CalculateService) getServletContext().getAttribute("calculateService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String type = req.getParameter("type");
        Double result;
        if (from.isEmpty() || to.isEmpty() || type.isEmpty()) resp.sendRedirect(req.getContextPath() + "?error=true");
        if(type.equals("Crowflight"))
            result = calculateService.crowflightFormula(from, to);
        else if (type.equals("Distance Matrix"))
            result = calculateService.distanceMatrix(from, to);
        else throw new IOException();
        resp.getWriter().write(String.valueOf(result));
        resp.getWriter().close();
    }
}
