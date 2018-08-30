package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.DistanceDao;
import org.magenta.test.task.entity.City;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CalculateServiceImpl implements CalculateService {

    private DistanceDao distanceDao;
    private CityDao cityDao;
    private static final int EARTH_RADIUS_METERS = 6_371_000;

    public CalculateServiceImpl(DistanceDao distanceDao, CityDao cityDao) {
        this.distanceDao = distanceDao;
        this.cityDao = cityDao;
    }

    private static List<City> fillInList(String[] list, CityDao cityDao) throws SQLException {
        List<City> cities = new ArrayList<>(list.length);

        for (int i = 0; i < list.length; i++) {
            String name = list[i].substring(0, 1).toUpperCase() + list[i].toLowerCase().substring(1);
            list[i] = name;
            City city = cityDao.findCityByName(name);
            assert city != null;
            cities.add(city);
        }
        return cities;
    }

    private static int countDistance(City from, City to) {
        double latDistance = Math.toRadians(to.getLatitude() - from.getLatitude());
        double lonDistance = Math.toRadians(to.getLongitude() - from.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(from.getLatitude())) * Math.cos(Math.toRadians(to.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return (int) Math.round(EARTH_RADIUS_METERS * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
    }

    @Override
    public List<Integer> crowflightFormula(String[] from, String[] to) {
        try {
            List<City> citiesFrom = fillInList(from, cityDao);
            List<City> citiesTo = fillInList(to, cityDao);
            List<Integer> result = new ArrayList<>(citiesFrom.size() + citiesTo.size());
            citiesFrom.forEach(f -> citiesTo.forEach(t -> result.add(countDistance(f, t))));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Integer> distanceMatrix(String[] from, String[] to) {
        List<Integer> result = new ArrayList<>(from.length + to.length);
        Stream.of(from).forEach(f -> Stream.of(to).forEach(t -> {
            try {
                Integer distance = distanceDao.findDistance(f, t);
                assert distance != null;
                result.add(distance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
        return result;
    }

    @Override
    public List<Integer> allCalculations(String from, String to) {
        String[] fromList = from.split(", ");
        String[] toList = to.split(", ");
        List<Integer> result = crowflightFormula(fromList, toList);
        result.addAll(distanceMatrix(fromList, toList));
        return result;
    }
}
