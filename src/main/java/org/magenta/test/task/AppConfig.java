package org.magenta.test.task;

import org.magenta.test.task.servlet.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(CitiesServlet.class, CalculateServlet.class, SendServlet.class).collect(Collectors.toSet());
    }
}
