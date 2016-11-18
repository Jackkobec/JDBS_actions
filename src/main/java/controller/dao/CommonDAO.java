package controller.dao;

import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public interface CommonDAO<ENTITY_CLASS, ID_TYPE> {

    List<ENTITY_CLASS> getAll();

    ENTITY_CLASS getOneByID(ID_TYPE id);

    boolean addNewEntity(ENTITY_CLASS entity);

    boolean updateEntityInfo(ENTITY_CLASS entity);

}
