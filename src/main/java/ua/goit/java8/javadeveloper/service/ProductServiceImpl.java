package ua.goit.java8.javadeveloper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.java8.javadeveloper.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 25/01/2018.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired(required = true)
    public void setProductDAO(HibernateProductDAOImpl productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public Product getById(UUID uuid) {
        return this.productDAO.getById(uuid);
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        return this.productDAO.getAll();
    }

    @Override
    @Transactional
    public List<Product> getByName(String name) {
        return this.productDAO.getByName(name);
    }

    @Override
    @Transactional
    public void create(Product product) {
        this.productDAO.create(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        this.productDAO.update(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        this.productDAO.delete(product);
    }
}
