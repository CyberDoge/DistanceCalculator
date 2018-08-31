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
            JAXBContext context = JAXBContext.newInstance(Cities.class, Distances.class);
            Unmarshaller un = context.createUnmarshaller();
            Object entity =  un.unmarshal(inputStream);
            if (entity instanceof Cities) {
                for (City city : ((Cities)entity).cityList) {
                    assert city != null;
                    cityDao.save(city);
                }
            }else if (entity instanceof Distances) {
                for (Distance distance : ((Distances)entity).distanceList) {
                    assert distance != null;
                    distanceDao.save(distance);
                }
            } else throw new JAXBException("Not valid entity");
        } catch (SQLException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
