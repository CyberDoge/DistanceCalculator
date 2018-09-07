import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.magenta.test.task.dao.CityDao;
import org.magenta.test.task.dao.CityDaoImpl;
import org.magenta.test.task.entity.City;
import org.magenta.test.task.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CityDaoTest {
    @BeforeAll
    public static void init(){
        assertDoesNotThrow(()->DbUtil.init());
    }
    @Test
    public void save() throws SQLException {
        CityDao cityDao = new CityDaoImpl();
        Connection connection = cityDao.openConnectionForSave();
        assertDoesNotThrow(()->cityDao.save(new City(228, "LLMM", 0.2f, 0.3f),connection));
        connection.close();
    }
}
