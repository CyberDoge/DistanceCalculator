package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;


@Path("cities")
public class CitiesServlet {
    private CityService cityService;


//    public void init() {
//        cityService = (CityService) getServletContext().getAttribute("cityService");
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, String> printCities()  {
        return cityService.getCities();
    }
}
