package controller.dao;

import model.Prepod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public class PrepodDAO implements CommonDAO<Prepod, Integer> {

    private Connection connection;

    public PrepodDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Prepod> getAll() {

        List<Prepod> prepods = new ArrayList<>();
        String SQLquery = "SELECT * FROM prepods";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

            while (resultSet.next()) {

                Prepod prepod = new Prepod();

                prepod.setId(resultSet.getInt("id"));
                prepod.setName(resultSet.getString("name"));
                prepod.setExperience(resultSet.getInt("experience"));
                prepod.setSubject_id(resultSet.getInt("subject_id"));

                prepods.add(prepod);
            }

            return prepods;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Prepod getOneByID(Integer id) {

        String SQLquery;

        if (null != id) {
            SQLquery = "SELECT * FROM prepods WHERE prepods.id = " + id + ";";
        } else throw new NullPointerException("Передано значение null");


        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQLquery)) {

            Prepod prepod = new Prepod();
            while (resultSet.next()) {

                prepod.setId(resultSet.getInt("id"));
                prepod.setName(resultSet.getString("name"));
                prepod.setExperience(resultSet.getInt("experience"));
                prepod.setSubject_id(resultSet.getInt("subject_id"));
            }

            return prepod;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
