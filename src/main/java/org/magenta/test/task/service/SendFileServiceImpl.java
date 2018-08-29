package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.DistanceDao;

import java.io.InputStream;

public class SendFileServiceImpl implements SendFileService {
    private DistanceDao distanceDao;
    private CityDao cityDao;

    public SendFileServiceImpl(DistanceDao distanceDao, CityDao cityDao) {
        this.distanceDao = distanceDao;
        this.cityDao = cityDao;
    }

    @Override
    public void saveToDb(InputStream inputStream) {

    }
}
