package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;
import util.DbUtil;

import java.sql.*;

public class DistanceDaoImpl implements DistanceDao {
    private static final String FIND_CITY_BY_NAME_QUERY =
            "SELECT latitude, longitude FROM city WHERE name=?;";
    private static final String FIND_DISTANCE_QUERY =
                    "SET @from_city := ?, @to_city := ?;" +
                    "select distance from distance" +
                    "where (from_city = @from_city or from_city = @to_city)" +
                    "  and (to_city = @to_city or to_city = @from_city)" +
                    "  and (to_city != from_city)";
    @Override
    public City findCityByName(String name) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CITY_BY_NAME_QUERY)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet.wasNull() == true) return null;
            return new City(name, resultSet.getFloat(1), resultSet.getFloat(2));
        } finally {
            resultSet.close();
        }
    }

    @Override
    public Integer findDistance(String from, String to) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DISTANCE_QUERY)) {
            statement.setString(1, from);
            statement.setString(2, to);
            if(resultSet.wasNull() == true) return null;
            return resultSet.getInt(1);
        }finally {
         resultSet.close();
        }

    }
}
