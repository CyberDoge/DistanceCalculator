package org.magenta.test.task.dao;

import org.magenta.test.task.entity.Distance;

import java.sql.Connection;
import java.sql.SQLException;

public interface DistanceDao {
    Integer findDistance(String from, String to) throws SQLException;
    void save(Distance distance, Connection connection) throws SQLException;
    Connection openConnectionForSave() throws SQLException;
    void closeConnectionForSave(Connection connection, boolean suc) throws SQLException;
}
