package org.magenta.test.task;

import org.magenta.test.task.servlet.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("")
public class AppConfig extends Application {
    private Set<Class<?>> classes;
    public AppConfig() {
        classes =  Stream.of(MainServlet.class, CalculateServlet.class, UploadServlet.class).collect(Collectors.toSet());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
