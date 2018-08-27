package org.magenta.test.task.dao;

import org.magenta.test.task.entity.City;

public interface DistanceDao {
    City findCityByName(String name);
    Double findDistance(String from, String to);
}
