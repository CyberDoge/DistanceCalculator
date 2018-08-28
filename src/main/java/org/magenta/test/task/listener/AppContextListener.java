package org.magenta.test.task.listener;

import org.magenta.test.task.dao.DistanceDaoImpl;
import org.magenta.test.task.service.CalculateServiceImpl;
import util.DbUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DbUtil.init(null);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        CalculateServiceImpl calculateService = new CalculateServiceImpl(new DistanceDaoImpl());
        servletContextEvent.getServletContext().setAttribute("calculateService", calculateService);

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
