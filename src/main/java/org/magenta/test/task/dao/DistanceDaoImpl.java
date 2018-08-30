package org.magenta.test.task.dao;

import org.magenta.test.task.entity.Distance;
import org.magenta.test.task.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DistanceDaoImpl implements DistanceDao {
    private static final String PREPEAR_DISTANCE_QUERY = "SET @from_city := (SELECT city_id FROM city WHERE name = ?)," +
            " @to_city := (SELECT city_id FROM city WHERE name = ?); ";

    private static final String FIND_DISTANCE_QUERY =
            "SELECT distance FROM distance " +
                    "WHERE (from_city = @from_city OR from_city = @to_city) " +
                    "AND (to_city = @to_city OR to_city = @from_city) " +
                    "AND (to_city != from_city)";
    private static final String SAVE_DISTANCE_TO_DB_QUERY = "INSERT INTO distance VALUES (?, ?, ?)";

    @Override
    public Integer findDistance(String from, String to) throws SQLException {
        ResultSet resultSet = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(PREPEAR_DISTANCE_QUERY)) {
            statement.setString(1, from);
            statement.setString(2, to);
            statement.execute();
            resultSet = statement.executeQuery(FIND_DISTANCE_QUERY);
            if (resultSet.next()) return resultSet.getInt(1);
            return null;
        } finally {
            if (resultSet != null)
                resultSet.close();
        }
    }

    @Override
    public void save(Distance distance) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_DISTANCE_TO_DB_QUERY)) {
            statement.setString(1, distance.getFromCity());
            statement.setString(2, distance.getToCity());
            statement.setInt(3, distance.getDistance());
            assert statement.execute();
        }
    }
}
