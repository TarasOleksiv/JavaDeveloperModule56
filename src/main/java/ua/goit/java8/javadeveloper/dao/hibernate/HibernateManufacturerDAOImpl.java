package ua.goit.java8.javadeveloper.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 27.01.2018.
 */

@SuppressWarnings("ALL")
@Repository
public class HibernateManufacturerDAOImpl implements ManufacturerDAO {
    private static final Logger logger = LoggerFactory.getLogger(HibernateManufacturerDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Manufacturer getById(UUID uuid) {

        Session session =this.sessionFactory.getCurrentSession();
        Manufacturer manufacturer = (Manufacturer) session.get(Manufacturer.class,uuid);
        logger.info("Manufacturer successfully loaded. Manufacturer details: " + manufacturer);

        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Manufacturer> manufacturers = (List<Manufacturer>) session.createQuery("FROM Manufacturer order by name").list();

        for(Manufacturer manufacturer: manufacturers){
            logger.info("Manufacturer list: " + manufacturer);
        }
        return manufacturers;
    }

    @Override
    public List<Manufacturer> getByName(String name) {
        Session session =this.sessionFactory.getCurrentSession();
        // HQL
        Query query = session.createQuery("FROM Manufacturer WHERE name = :name");
        query.setParameter("name",name);
        List<Manufacturer> manufacturers = (List<Manufacturer>) query.list();

        for(Manufacturer manufacturer: manufacturers){
            logger.info("Manufacturer list: " + manufacturer);
        }

        return manufacturers;
    }

    @Override
    public void create(Manufacturer manufacturer) {
        Session session =this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Manufacturer WHERE name = :name");
        query.setParameter("name",manufacturer.getName());
        List<Manufacturer> results = (List<Manufacturer>) query.list();
        if (results.size() > 0){
            logger.info("Manufacturer with name " + manufacturer.getName() + " exists already. It's impossible to create manufacturer");
            } else {
            session.save(manufacturer);
            logger.info("Manufacturer successfully saved. Manufacturer details: " + manufacturer);
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        Session session =this.sessionFactory.getCurrentSession();
        session.update(manufacturer);
        logger.info("Manufacturer successfully updated. Manufacturer details: " + manufacturer);
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        Session session =this.sessionFactory.getCurrentSession();
        session.delete(manufacturer);
        logger.info("Manufacturer successfully deleted. Manufacturer details: " + manufacturer);
    }
}
