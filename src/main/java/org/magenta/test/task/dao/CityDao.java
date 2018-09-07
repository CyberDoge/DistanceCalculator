package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface CityDao {
    void save(City city, Connection connection) throws SQLException;
    City findCityByName(String name) throws SQLException;
    Map<Integer, String> findAllCitiesIdAndName() throws SQLException;
    Connection openConnectionForSave() throws SQLException;
    void closeConnectionForSave(Connection connection, boolean suc) throws SQLException;
}
