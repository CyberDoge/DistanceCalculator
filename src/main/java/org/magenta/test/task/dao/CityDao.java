package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;

import java.sql.SQLException;

public interface CityDao {
    void save(City city) throws SQLException;
    City findCityByName(String name) throws SQLException;

}
