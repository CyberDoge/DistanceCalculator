package org.magenta.test.task.listener;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.CityDaoImpl;
import org.magenta.test.task.dao.DistanceDao;
import org.magenta.test.task.dao.DistanceDaoImpl;
import org.magenta.test.task.service.*;
import org.magenta.test.task.util.DbUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DbUtil.init();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        DistanceDao distanceDao = new DistanceDaoImpl();
        CityDao cityDao = new CityDaoImpl();
        CalculateServiceImpl calculateService = new CalculateServiceImpl(distanceDao, cityDao);
        servletContextEvent.getServletContext().setAttribute("calculateService", calculateService);
        UploadFileService uploadFileService = new UploadFileServiceImpl(distanceDao, cityDao);
        servletContextEvent.getServletContext().setAttribute("uploadFileService", uploadFileService);
        CityService cityService = new CityServiceImpl(cityDao);
        servletContextEvent.getServletContext().setAttribute("cityService", cityService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
