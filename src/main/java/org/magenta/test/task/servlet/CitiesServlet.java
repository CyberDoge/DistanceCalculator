package org.magenta.test.task.servlet;

import org.magenta.test.task.service.CityService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;


@Path("cities")
public class CitiesServlet {
    @Inject
    private CityService cityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, String> printCities() {
        return cityService.getCities();
    }
}
