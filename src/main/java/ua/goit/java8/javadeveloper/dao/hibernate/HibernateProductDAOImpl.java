package ua.goit.java8.javadeveloper.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 31/01/2018.
 */
@Repository
public class HibernateProductDAOImpl implements ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(HibernateProductDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Product getById(UUID uuid) {
        Session session =this.sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class,uuid);
        logger.info("Product successfully loaded. Product details: " + product);

        return product;
    }

    @Override
    public List<Product> getAll() {
        Session session =this.sessionFactory.getCurrentSession();
        List<Product> products = (List<Product>) session.createQuery("FROM Product order by name").list();

        for(Product product: products){
            logger.info("Product list: " + product);
        }
        return products;
    }

    @Override
    public List<Product> getByName(String name) {
        Session session =this.sessionFactory.getCurrentSession();
        // HQL
        Query query = session.createQuery("FROM Product WHERE name = :name");
        query.setParameter("name",name);
        List<Product> products = (List<Product>) query.list();

        for(Product product: products){
            logger.info("Product list: " + product);
        }

        return products;
    }

    @Override
    public void create(Product product) {
        Session session =this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Product WHERE name = :name AND manufacturer = :manufacturer");
        query.setParameter("name",product.getName());
        query.setParameter("manufacturer",product.getManufacturer());
        List<Product> results = (List<Product>) query.list();
        if (results.size() > 0){
            logger.info("Product with name " + product.getName() + " for manufacturer '" + product.getManufacturer().getName() + "' exists already. It's impossible to create product");
        } else {
            session.save(product);
            logger.info("Product successfully saved. Product details: " + product);
        }
    }

    @Override
    public void update(Product product) {
        Session session =this.sessionFactory.getCurrentSession();
        session.update(product);
        logger.info("Product successfully updated. Product details: " + product);
    }

    @Override
    public void delete(Product product) {
        Session session =this.sessionFactory.getCurrentSession();
        session.delete(product);
        logger.info("Product successfully deleted. Product details: " + product);
    }
}
