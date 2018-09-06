package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CityService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Path("")
public class MainServlet {
    @Context
    ServletContext servletContext;
    @Inject
    private CityService cityService;

    @GET
    @Path("cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, String> printCities() {
        return cityService.getCities();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream openMainPage() throws IOException {
        return new FileInputStream(servletContext.getAttribute("basedir") + "/index.html");
    }
}
