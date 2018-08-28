package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;

import java.sql.SQLException;

public interface DistanceDao {
    City findCityByName(String name) throws SQLException;
    Integer findDistance(String from, String to) throws SQLException;
}
