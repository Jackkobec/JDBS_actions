package controller.dao;

import java.util.List;

/**
 * Created by Jack on 18.11.2016.
 */
public interface CommonDAO<E, I> {

    List<E> getAll();

    E getOneByID(I id);

    boolean addNewEntity(E entity);

    boolean updateEntityInfo(E entity);

}
