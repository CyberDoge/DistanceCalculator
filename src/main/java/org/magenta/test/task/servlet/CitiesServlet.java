package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CityService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cities")
public class CitiesServlet extends HttpServlet {
    private CityService cityService;

    @Override
    public void init() {
        cityService = (CityService) getServletContext().getAttribute("cityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder res = new StringBuilder();
        cityService.getCities().forEach((k, v) -> res.append(k).append(' ').append(v).append('\n'));
        resp.getWriter().write(res.toString());
    }
}
