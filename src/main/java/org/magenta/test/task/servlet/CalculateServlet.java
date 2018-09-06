package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CalculateService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

@Path("calculate")
public class CalculateServlet {

    @Context
    ServletContext servletContext;
    @Inject
    private CalculateService calculateService;

    @GET
    @Produces("text/html")
    public InputStream openPage() throws IOException {
        return new FileInputStream(servletContext.getAttribute("basedir") + "/calculate.html");
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public List<Integer> doPost(@FormParam("from") String from, @FormParam("to") String to,
                                @FormParam("type") String type) throws IOException {
        List<Integer> result;
        if (from.isEmpty() || to.isEmpty() || type.isEmpty())
            Response.temporaryRedirect(URI.create("/calculate?error=true"));
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
