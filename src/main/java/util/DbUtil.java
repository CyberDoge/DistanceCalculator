package util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static final BasicDataSource dataSource = new BasicDataSource();

    public static void init() throws IOException, ClassNotFoundException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream("database.properties");
        prop.load(resourceStream);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        Class.forName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        resourceStream.close();
    }

    public static void close() throws SQLException {
        dataSource.close();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}