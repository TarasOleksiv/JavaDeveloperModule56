package ua.goit.java8.javadeveloper.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.goit.java8.javadeveloper.dao.UserDAO;
import ua.goit.java8.javadeveloper.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 01/02/2018.
 */
public class HibernateUserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(HibernateProductDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getById(UUID uuid) {
        Session session =this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class,uuid);
        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @Override
    public List<User> getAll() {
        Session session =this.sessionFactory.getCurrentSession();
        List<User> users = (List<User>) session.createQuery("FROM User order by lastName, firstName").list();

        for(User user: users){
            logger.info("User list: " + user);
        }
        return users;
    }

    @Override
    public List<User> getByName(String name) {
        Session session =this.sessionFactory.getCurrentSession();
        // HQL
        Query query = session.createQuery("FROM User WHERE username = :name");
        query.setParameter("name",name);
        List<User> users = (List<User>) query.list();

        for(User user: users){
            logger.info("User list: " + user);
        }

        return users;
    }

    @Override
    public void create(User user) {
        Session session =this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE username = :name");
        query.setParameter("name",user.getUsername());
        List<User> results = (List<User>) query.list();
        if (results.size() > 0){
            logger.info("User with username " + user.getUsername() + " exists already. It's impossible to create user");
        } else {
            session.save(user);
            logger.info("User successfully saved. User details: " + user);
        }
    }

    @Override
    public void update(User user) {
        Session session =this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully updated. User details: " + user);
    }

    @Override
    public void delete(User user) {
        Session session =this.sessionFactory.getCurrentSession();
        session.delete(user);
        logger.info("User successfully deleted. User details: " + user);
    }
}
