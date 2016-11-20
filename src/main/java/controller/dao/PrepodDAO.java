package controller.dao;

import model.Prepod;

import java.sql.*;
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


        String SQLquery = "SELECT * FROM prepods";

        return getListOfPrepodsBySQLquery(SQLquery);
    }

    //java realisation
    public List<Prepod> getAllPrepodsWithExperienceMoreThen3Years() {

        List<Prepod> prepods = new ArrayList<>();

        for (Prepod prepod : getAll()) {

            if (prepod.getExperience() >= 3) {
                prepods.add(prepod);
            }
        }

        return prepods;
    }

    //SQL realisation
    public List<Prepod> getAllPrepodsByExperience(int experience) {


        String SQLquery = "SELECT * FROM prepods WHERE prepods.experience >= " + experience + ";";

        return getListOfPrepodsBySQLquery(SQLquery);
    }

    private List<Prepod> getListOfPrepodsBySQLquery(String SQLquery) {

        if (null == SQLquery) {
            throw new NullPointerException("Передан пустой SQLquery");
        }

        try (ResultSet resultSet = connection.prepareStatement(SQLquery).executeQuery(SQLquery)) {

            List<Prepod> prepods = new ArrayList<>();

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


        try (ResultSet resultSet = connection.prepareStatement(SQLquery).executeQuery(SQLquery)) {

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

    @Override
    public boolean addNewEntity(Prepod entity) {

        String SQLquery;

        if (null != entity) {
            SQLquery = "INSERT INTO prepods(name, experience, subject_id) VALUES (?, ?, ?)";
        } else return false;

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    @Override
    public boolean updateEntityInfo(Prepod entity) {


        String SQLquery = "UPDATE groups SET name = ?. experience = ?, subject_id = ? " +
                "WHERE id = " + entity.getId() + ";";

        if (getOneByID(entity.getId()) == null) {
            return false;
        }

        return (executeQueryInPreparedStatement(entity, SQLquery)) ? true : false;
    }

    private boolean executeQueryInPreparedStatement(Prepod entity, String SQLquery) {

        if (null == SQLquery || entity == null) {
            throw new NullPointerException("Передан пустой SQLquery / entity");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLquery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getExperience());
            preparedStatement.setInt(3, entity.getSubject_id());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
