package org.magenta.test.task.service;

import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.DistanceDao;
import org.magenta.test.task.entity.City;
import org.magenta.test.task.entity.Distance;
import org.magenta.test.task.helper.Cities;
import org.magenta.test.task.helper.Distances;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Stateless
public class UploadFileServiceImpl implements UploadFileService {
    private DistanceDao distanceDao;
    private CityDao cityDao;

    @Inject
    public UploadFileServiceImpl(DistanceDao distanceDao, CityDao cityDao) {
        this.distanceDao = distanceDao;
        this.cityDao = cityDao;
    }

    @Override
    public void saveToDb(InputStream inputStream) {

        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class, Distances.class);
            Unmarshaller un = context.createUnmarshaller();
            Object entity = un.unmarshal(inputStream);
            if (entity instanceof Cities) {
                Connection connection = cityDao.openConnectionForSave();
                for (City city : ((Cities) entity).cityList) {
                    assert city != null;
                    try {
                        cityDao.save(city, connection);
                    } catch (SQLException e) {
                        cityDao.closeConnectionForSave(connection, false);
                        e.printStackTrace();
                    }
                }
                cityDao.closeConnectionForSave(connection, true);
            } else if (entity instanceof Distances) {
                Connection connection = distanceDao.openConnectionForSave();
                for (Distance distance : ((Distances) entity).distanceList) {
                    assert distance != null;
                    try {
                        distanceDao.save(distance, connection);
                    } catch (SQLException e) {
                        distanceDao.closeConnectionForSave(connection, false);
                        e.printStackTrace();
                    }
                }
                distanceDao.closeConnectionForSave(connection, false);
            } else throw new JAXBException("Not valid entity");
        } catch (SQLException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
