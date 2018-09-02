package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CalculateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@Path("calculate")
public class CalculateServlet {
    private CalculateService calculateService;

//    public void init() throws ServletException {
//        calculateService = (CalculateService) getServletContext().getAttribute("calculateService");
    //}


    @GET
    @Produces("text/html")
    public void openPage(@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("calculate.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public List<Integer> doPost(@FormParam("from") String from, @FormParam("to") String to,
                                @FormParam("type") String type) throws IOException {

        List<Integer> result;
        if (from.isEmpty() || to.isEmpty() || type.isEmpty())
            Response.temporaryRedirect(URI.create( "/calculate?error=true"));
        switch (type) {
            case "Crowflight":
                result = calculateService.crowflightFormula(from.split(", "), to.split(", "));
                break;
            case "Distance Matrix":
                result = calculateService.distanceMatrix(from.split(", "), to.split(", "));
                break;
            case "all":
                result = calculateService.allCalculations(from, to);
                break;
            default:
                throw new IOException();
        }
        return result;
    }
}
