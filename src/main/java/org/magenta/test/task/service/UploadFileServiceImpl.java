package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.DistanceDao;
import org.magenta.test.task.entity.City;
import org.magenta.test.task.entity.Distance;
import org.magenta.test.task.helper.Cities;
import org.magenta.test.task.helper.Distances;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class UploadFileServiceImpl implements UploadFileService {
    private DistanceDao distanceDao;
    private CityDao cityDao;

    public UploadFileServiceImpl(DistanceDao distanceDao, CityDao cityDao) {
        this.distanceDao = distanceDao;
        this.cityDao = cityDao;
    }

    @Override
    public void saveToDb(InputStream inputStream) {
        try {
            for (City city : citiesFromXml(inputStream)) {
                assert city != null;
                cityDao.save(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
            /*
            try {
                for (Distance distance : distanceFromXml(inputStream)) {
                    assert distance != null;
                    distanceDao.save(distance);
                }
            } catch (JAXBException | SQLException e1) {
                e1.printStackTrace();
            }*/
        }
    }

    private static List<City> citiesFromXml(InputStream inputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Cities.class);
        Unmarshaller un = context.createUnmarshaller();
        Cities cities = (Cities) un.unmarshal(inputStream);
        return cities.cityList;
    }

    private static List<Distance> distanceFromXml(InputStream inputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Distances.class);
        Unmarshaller un = context.createUnmarshaller();
        Distances distances = (Distances) un.unmarshal(inputStream);
        return distances.getDistances();
    }
}
