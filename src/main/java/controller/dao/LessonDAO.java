package controller.dao;

import model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public class LessonDAO implements CommonDAO<Lesson, Integer> {

    private Connection connection;

    public LessonDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Lesson> getAll() {

        List<Lesson> lessons = new ArrayList<>();
        String SQLquery = "SELECT * FROM lessons";

        try (ResultSet resultSet = connection.prepareStatement(SQLquery).executeQuery(SQLquery)) {

            while (resultSet.next()) {

                Lesson lesson = new Lesson();

                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDescription(resultSet.getString("description"));

                lessons.add(lesson);
            }

            return lessons;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Lesson getOneByID(Integer id) {

        String SQLquery;

        if (null != id) {
            SQLquery = "SELECT * FROM lessons WHERE lessons.id = " + id + ";";
        } else throw new NullPointerException("Передано значение null");


        try (ResultSet resultSet = connection.prepareStatement(SQLquery).executeQuery(SQLquery)) {

            Lesson lesson = new Lesson();
            while (resultSet.next()) {

                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setDescription(resultSet.getString("description"));
            }

            return lesson;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addNewEntity(Lesson entity) {

        String SQLquery;

        if (null != entity) {
            SQLquery = "INSERT INTO lessons(name, description) VALUES (?, ?)";
        } else return false;

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    @Override
    public boolean updateEntityInfo(Lesson entity) {

        String SQLquery = "UPDATE lessons SET name = ?, description = ? WHERE id = " + entity.getId() + ";";

        if (getOneByID(entity.getId()) == null) {
            return false;
        }

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    private boolean executeQueryInPreparedStatement(Lesson entity, String SQLquery) {

        if (null == SQLquery || entity == null) {
            throw new NullPointerException("Передан пустой SQLquery / entity");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
