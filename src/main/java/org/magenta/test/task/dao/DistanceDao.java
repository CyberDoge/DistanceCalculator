package org.magenta.test.task.dao;

import org.magenta.test.task.entity.Distance;

import java.sql.SQLException;

public interface DistanceDao {
    Integer findDistance(String from, String to) throws SQLException;
    void save(Distance distance) throws SQLException;
}
