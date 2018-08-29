package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.DistanceDao;
import org.magenta.test.task.entity.City;

import java.sql.SQLException;

public class CalculateServiceImpl implements CalculateService {

    private DistanceDao distanceDao;
    private CityDao cityDao;
    private static final int EARTH_RADIUS_METERS = 6_371_000;

    public CalculateServiceImpl(DistanceDao distanceDao, CityDao cityDao) {
        this.distanceDao = distanceDao;
        this.cityDao = cityDao;
    }

    @Override
    public Double crowflightFormula(String from, String to) {
        try {
            City fromCity = cityDao.findCityByName(from);
            City toCity = cityDao.findCityByName(to);
            double latDistance = Math.toRadians(toCity.getLatitude() - fromCity.getLatitude());
            double lonDistance = Math.toRadians(toCity.getLongitude() - fromCity.getLongitude());
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(fromCity.getLatitude())) * Math.cos(Math.toRadians(toCity.getLatitude()))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            return EARTH_RADIUS_METERS * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer distanceMatrix(String from, String to) {
        try {
            return distanceDao.findDistance(from, to);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
