package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;

import java.util.Map;

public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    public CityServiceImpl(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Map<Integer, String> getCities() {
        return null;
    }
}
