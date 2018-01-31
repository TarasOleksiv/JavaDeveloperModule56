package ua.goit.java8.javadeveloper.dao;

import java.util.List;

/**
 * Created by t.oleksiv on 09/11/2017.
 */
public interface GenericDAO<T, ID, N> {

    T getById(ID id);

    List<T> getAll();

    List<T> getByName(N name);

    void create(T t);

    void update(T t);

    void delete(T t);

}