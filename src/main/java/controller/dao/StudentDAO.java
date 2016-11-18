package controller.dao;

import model.Group;
import model.Student;

import java.sql.*;
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

        String SQLquery = "SELECT * FROM students";

        return getListOfStudentsBySQLquery(SQLquery);
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

    @Override
    public boolean addNewEntity(Student entity) {

        String SQLquery;

        if (null != entity) {
            SQLquery = "INSERT INTO students(name, group_id) VALUES (?, ?)";
        } else return false;

        if (executeQueryInPreparedStatement(entity, SQLquery)) return false;

        return true;
    }

    @Override
    public boolean updateEntityInfo(Student entity) {


        String SQLquery = "UPDATE students SET name = ?, group_id = ? WHERE id = " + entity.getId() + ";";

        if (getOneByID(entity.getId()) == null) {
            return false;
        }

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    private boolean executeQueryInPreparedStatement(Student entity, String SQLquery) {

        if (null == SQLquery || entity == null) {
            throw new NullPointerException("Передан пустой SQLquery / entity");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getGroup_id());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public List<Student> getStudentsByGroup(Group group) {

        String SQLquery;


        if (null != group) {
            SQLquery = "SELECT * FROM students WHERE students.group_id = " + group.getId() + ";";

        } else throw new NullPointerException("Передано значение null");

        return getListOfStudentsBySQLquery(SQLquery);

    }

    private List<Student> getListOfStudentsBySQLquery(String SQLquery) {

        if (null == SQLquery) {
            throw new NullPointerException("Передан пустой SQLquery");
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

            List<Student> students = new ArrayList<>();

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


}

