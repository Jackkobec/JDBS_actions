import persestince.PrepodDAO;
import model.Prepod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Jack on 23.11.2016.
 */
public class TestPrepodDAO {

    private final static String PATH_TO_PROPERTIES = "src/main/resources/app.properties";
    private Connection connection;
    private Properties properties;
    private PrepodDAO prepodDAO;

    @Before
    public void init() throws IOException, SQLException {

        properties = new Properties();
        properties.load(new FileInputStream(new File(PATH_TO_PROPERTIES)));

        connection = DriverManager.getConnection(
                properties.getProperty("URL"),
                properties.getProperty("USER"),
                properties.getProperty("PASSWORD"));

        prepodDAO = new PrepodDAO();
    }

    @After
    public void closeConnection() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void selectPrepodWithMaxExperience() {

        Prepod prepod = new Prepod();

        prepod = prepodDAO.getPrepodsWithMaxExperience();
        String expected = "Prepod(id=4, name=Solomonovich, experience=5, lesson_id=4)";

        Assert.assertEquals(expected, prepod.toString());

    }

    @Test
    public void selectPrepodWithMinExperience() {

        Prepod prepod = new Prepod();

        prepod = prepodDAO.getPrepodsWithMinExperience();
        String expected = "Prepod(id=1, name=Petrovich, experience=2, lesson_id=1)";

        Assert.assertEquals(expected, prepod.toString());

    }

}
