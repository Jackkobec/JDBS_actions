package controller.dao;

import model.Group;
import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public class GroupDAO implements CommonDAO<Group, Integer> {

    private Connection connection;

    public GroupDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Group> getAll() {

        String SQLquery = "SELECT * FROM groups";

        return getListOfGroupsBySQLquery(SQLquery);
    }

    @Override
    public Group getOneByID(Integer id) {

        String SQLquery;

        if (null != id) {
            SQLquery = "SELECT * FROM groups WHERE groups.id = " + id;
        } else throw new NullPointerException("Передано значение null");

        return getGroupBySQLquery(SQLquery);
    }

    @Override
    public boolean addNewEntity(Group entity) {

        String SQLquery;

        if (null != entity) {
            SQLquery = "INSERT INTO groups(name) VALUES (?)";
        } else return false;

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    @Override
    public boolean updateEntityInfo(Group entity) {


        String SQLquery = "UPDATE groups SET name = ? WHERE id = " + entity.getId() + ";";

        if (getOneByID(entity.getId()) == null) {
            return false;
        }

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    private boolean executeQueryInPreparedStatement(Group entity, String SQLquery) {

        if (null == SQLquery || entity == null) {
            throw new NullPointerException("Передан пустой SQLquery / entity");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Group getOneByName(String groupName) {

        String SQLquery = "";

        if (null != groupName) {
            SQLquery = "SELECT * FROM groups WHERE groups.name = " + "'" + groupName + "'";
        } else {
            throw new NullPointerException("groupName == null");
        }

        return getGroupBySQLquery(SQLquery);
    }

    public List<Group> getGroupsByLesson(String lesson) {

        String SQLquery = "";

        if (null != lesson) {
            SQLquery = "SELECT groups.id, groups.name FROM learning LEFT JOIN lessons ON learning.lesson_id = lessons.id " +
                    "RIGHT JOIN groups ON learning.group_id = groups.id WHERE lessons.name = " + "'" + lesson + "'";
        } else {
            throw new NullPointerException("lesson == null");
        }

        return getListOfGroupsBySQLquery(SQLquery);

    }

    private List<Group> getListOfGroupsBySQLquery(String SQLquery) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery);
             ResultSet resultSet = preparedStatement.executeQuery(SQLquery)) {

            List<Group> groups = new ArrayList<>();

            while (resultSet.next()) {

                Group group = new Group();

                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));

                groups.add(group);
            }

            return groups;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Group getGroupBySQLquery(String SQLquery) {

        if (null == SQLquery) {
            throw new NullPointerException("Передан пустой SQLquery");
        }

        try (ResultSet resultSet = connection.prepareStatement(SQLquery).executeQuery(SQLquery)) {
//             PreparedStatement preparedStatement = connection.prepareStatement(SQLquery);
//             ResultSet resultSet = preparedStatement.executeQuery(SQLquery)) {

            Group group = new Group();
            while (resultSet.next()) {

                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));

            }

            return group;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
