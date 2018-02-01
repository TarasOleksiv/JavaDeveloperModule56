package ua.goit.java8.javadeveloper.dao;

import ua.goit.java8.javadeveloper.model.User;

import java.util.UUID;

/**
 * Created by t.oleksiv on 30/11/2017.
 */
public interface UserDAO extends GenericDAO<User,UUID,String> {
}
