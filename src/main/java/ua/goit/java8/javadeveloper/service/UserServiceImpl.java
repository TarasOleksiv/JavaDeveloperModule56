package ua.goit.java8.javadeveloper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java8.javadeveloper.dao.UserDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateUserDAOImpl;
import ua.goit.java8.javadeveloper.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 01/02/2018.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired(required = true)
    public void setUserDAO(HibernateUserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User getById(UUID uuid) {
        return this.userDAO.getById(uuid);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    @Transactional
    public List<User> getByName(String name) {
        return this.userDAO.getByName(name);
    }

    @Override
    @Transactional
    public void create(User user) {
        this.userDAO.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        this.userDAO.update(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        this.userDAO.delete(user);
    }
}
