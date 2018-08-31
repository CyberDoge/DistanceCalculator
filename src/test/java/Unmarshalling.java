import org.junit.jupiter.api.Test;
import org.magenta.test.task.helper.Cities;
import org.magenta.test.task.helper.Distances;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Unmarshalling {
    @Test
    public void tryUnmarshall() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Distances.class, Cities.class);
        Unmarshaller un = context.createUnmarshaller();
        InputStream inputStream = new FileInputStream("cities.xml");
        Cities distances = (Cities) un.unmarshal(inputStream);
        distances.cityList.forEach(c -> System.out.println(c.getName()));
    }
}
