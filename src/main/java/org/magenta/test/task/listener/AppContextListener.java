package org.magenta.test.task.listener;

import util.DbUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DbUtil.init(new File("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        servletContextEvent.getServletContext().setAttribute("calculateService", null);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
