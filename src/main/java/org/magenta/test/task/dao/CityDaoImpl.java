package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;
import org.magenta.test.task.util.DbUtil;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class CityDaoImpl implements CityDao {
    private static final String FIND_CITY_BY_NAME_QUERY =
            "SELECT city_id, latitude, longitude FROM city WHERE name=?";
    private static final String SAVE_CITY_TO_DB_QUERY =
            "INSERT INTO city (name, latitude, longitude) VALUES (?, ?, ?)";
    private static final String FIND_CITIES_ID_NAME_QUERY =
            "SELECT city_id, name FROM city";

    @Override
    public void save(City city) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_CITY_TO_DB_QUERY)) {
            statement.setString(1, city.getName());
            statement.setFloat(2, city.getLatitude());
            statement.setFloat(3, city.getLongitude());
            statement.executeUpdate();
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

    @Override
    public Map<Integer, String> findAllCitiesIdAndName() throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_CITIES_ID_NAME_QUERY)) {
            HashMap<Integer, String> res = new HashMap<>();
            while (resultSet.next()) {
                res.put(resultSet.getInt(1), resultSet.getString(2));
            }
            return res;
        }
    }
}
