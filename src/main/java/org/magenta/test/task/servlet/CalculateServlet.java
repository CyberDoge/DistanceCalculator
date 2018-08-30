package org.magenta.test.task.servlet;

import com.google.gson.Gson;
import org.magenta.test.task.service.CalculateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet {
    private CalculateService calculateService;

    @Override
    public void init() throws ServletException {
        calculateService = (CalculateService) getServletContext().getAttribute("calculateService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("calculate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String type = req.getParameter("type");
        List<Integer> result;
        if (from.isEmpty() || to.isEmpty() || type.isEmpty()) resp.sendRedirect(req.getContextPath() + "?error=true");
        if (type.equals("Crowflight"))
            result = calculateService.crowflightFormula(from.split(", "), to.split(", "));
        else if (type.equals("Distance Matrix"))
            result = calculateService.distanceMatrix(from.split(", "), to.split(", "));
        else if (type.equals("all"))
            result = calculateService.allCalculations(from, to);
        else throw new IOException();
        String jsonObj = new Gson().toJson(result);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonObj);
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
