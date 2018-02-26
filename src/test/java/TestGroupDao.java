import persestince.GroupDAO;
import model.Group;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Jack on 20.11.2016.
 */
public class TestGroupDao {

    private final static String PATH_TO_PROPERTIES = "src/main/resources/app.properties";
    private Connection connection;
    private Properties properties;
    private GroupDAO groupDAO;

    @Before
    public void init() throws IOException, SQLException, ClassNotFoundException {

        properties = new Properties();
        properties.load(new FileInputStream(new File(PATH_TO_PROPERTIES)));

//        connection = DriverManager.getConnection(
//                properties.getProperty("URL"),
//                properties.getProperty("USER"),
//                properties.getProperty("PASSWORD"));

//        Class.forName("org.h2.Driver");
//        connection = DriverManager.
//                getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
//        groupDAO = new GroupDAO(connection);
//        System.out.println(connection);

        groupDAO = new GroupDAO();
    }

//    @After
//    public void closeConnection() {
//
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testGetAllGroups() {

        String expected = "[Group(id=1, name=group1), Group(id=2, name=group2), Group(id=3, name=group3)]";
        List<Group> students = groupDAO.getAll();
        System.out.println(students);

//        Assert.assertEquals(expected, students.toString());
    }

    @Test
    public void getGroupByID() {

        String expected = "Group(id=1, name=group1)";
        Group group = groupDAO.getOneByID(1);

        Assert.assertEquals(expected, group.toString());

    }

    @Test
    public void getGroupByName() {

        String expected = "Group(id=2, name=group2)";
        Group group = groupDAO.getOneByName("group2");

        Assert.assertEquals(expected, group.toString());

    }

    @Test
    public void addGroup() {

        Assert.assertTrue(groupDAO.addNewEntity(new Group(-1, "newGroup2")));

    }

    @Test
    public void updateGroup() {

        Assert.assertTrue(groupDAO.updateEntityInfo(new Group(4, "newGroupUpdate")));

    }

    @Test
    public void selectGroupsbyLesson() {

        String lesson = "java";
        List<Group> groups = groupDAO.getGroupsByLesson(lesson);
        String expected = "[Group(id=1, name=group1), Group(id=3, name=group3)]";

        Assert.assertEquals(expected, groups.toString());

    }

    @Test
    public void selectGroupsWithAllLessons() {

        List<Group> groups = groupDAO.getGroupsWhoLearnAllLessons();

        String expected = "[Group(id=1, name=group1), Group(id=3, name=group3)]";

        Assert.assertEquals(expected, groups.toString());

    }

    @Test
    public void selectGroupsWithMoreThan3StudentsLearnLesson() {

        String lesson = "philosophy";
        List<Group> groups = groupDAO.getGroupsWithMoreThan3StudentsLearnLesson(lesson);

        String expected = "[Group(id=1, name=Vasa), Group(id=3, name=Vana)]";

        Assert.assertEquals(expected, groups.toString());

    }
}
