package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;
import org.magenta.test.task.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao {
    private static final String FIND_CITY_BY_NAME_QUERY =
            "SELECT city_id, latitude, longitude FROM city WHERE name=?";
    private static final String SAVE_CITY_TO_DB_QUERY =
            "INSERT INTO city VALUES (?, ?, ?)";
    @Override
    public void save(City city) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_CITY_TO_DB_QUERY)) {
            statement.setString(1, city.getName());
            statement.setFloat(2, city.getLatitude());
            statement.setFloat(3, city.getLongitude());
            assert statement.execute();
        }
    }

    @Override
    public City findCityByName(String name) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CITY_BY_NAME_QUERY)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) return null;
            resultSet.next();
            return new City(resultSet.getInt(1), name, resultSet.getFloat(2), resultSet.getFloat(3));
        } finally {
            resultSet.close();
        }
    }
}
