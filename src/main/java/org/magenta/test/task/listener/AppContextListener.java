package org.magenta.test.task.listener;

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
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        servletContextEvent.getServletContext().setAttribute("basedir", path.substring(5, path.lastIndexOf('/', path.lastIndexOf('/') - 1)));
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
