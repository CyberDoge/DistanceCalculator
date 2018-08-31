import org.junit.jupiter.api.Test;
import org.magenta.test.task.entity.City;
import org.magenta.test.task.helper.Cities;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateXml {
    @Test
    public void generateCities() throws JAXBException, IOException {
        int size = (int) (Math.random() * 100) + 1000;
        List<City> citiesList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            citiesList.add(new City(i, UUID.randomUUID().toString().substring(0, 25), ((float) Math.random()), ((float) Math.random())));
        }
        JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Cities cities = new Cities();
        cities.cityList = citiesList;

        File file = new File("cities.xml");
        jaxbMarshaller.marshal(cities, file);
    }
}
