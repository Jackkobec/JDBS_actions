package controller.dao;

import model.Lesson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

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


        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

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
}
