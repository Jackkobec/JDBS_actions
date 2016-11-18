package controller.dao;

import model.Group;

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

        List<Group> groups = new ArrayList<>();
        String SQLquery = "SELECT * FROM groups";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

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

    @Override
    public Group getOneByID(Integer id) {

        String SQLquery = "";

        if (null != id) {
            SQLquery = "SELECT * FROM groups WHERE groups.id = " + id + ";";
        } else throw new NullPointerException("Передано значение null");

        return getGroupBySQLquery(SQLquery);
    }

    @Override
    public boolean addNewEntity(Group entity) {

        String SQLquery;

        if (null != entity) {
            SQLquery = "INSERT INTO groups(name) VALUES (?)";
        } else return false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean updateEntityInfo(Group entity) {


        String sqlQuery = "UPDATE groups SET name = ? WHERE id = " + entity.getId() + ";";

        if (getOneByID(entity.getId()) == null) {
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

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
            SQLquery = "SELECT * FROM groups WHERE groups.id = " + groupName + ";";
        }

        return getGroupBySQLquery(SQLquery);
    }


    private Group getGroupBySQLquery(String SQLquery) {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

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
