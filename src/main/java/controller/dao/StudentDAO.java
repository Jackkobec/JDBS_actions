package controller.dao;

import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public class StudentDAO implements CommonDAO<Student, Integer> {

    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Student> getAll() {

        List<Student> students = new ArrayList<>();
        String SQLquery = "SELECT * FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

            while (resultSet.next()) {

                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setGroup_id(resultSet.getInt("group_id"));

                students.add(student);
            }

            return students;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student getOneByID(Integer id) {

        String SQLquery;

        if (null != id) {
            SQLquery = "SELECT * FROM students WHERE students.id = " + id + ";";
        } else throw new NullPointerException("Передано значение null");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

            Student student = new Student();
            while (resultSet.next()) {

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setGroup_id(resultSet.getInt("group_id"));
            }

            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

