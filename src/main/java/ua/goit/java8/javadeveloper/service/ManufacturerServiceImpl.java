package ua.goit.java8.javadeveloper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.List;
import java.util.UUID;

/**
 * Created by t.oleksiv on 25/01/2018.
 */

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerDAO manufacturerDAO;

    @Autowired(required = true)
    public void setManufacturerDAO(HibernateManufacturerDAOImpl manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    @Transactional
    public Manufacturer getById(UUID uuid) {
        return this.manufacturerDAO.getById(uuid);
    }

    @Override
    @Transactional
    public List<Manufacturer> getAll() {
        return this.manufacturerDAO.getAll();
    }

    @Override
    @Transactional
    public List<Manufacturer> getByName(String name) {
        return this.manufacturerDAO.getByName(name);
    }

    @Override
    @Transactional
    public void create(Manufacturer manufacturer) {
        this.manufacturerDAO.create(manufacturer);
    }

    @Override
    @Transactional
    public void update(Manufacturer manufacturer) {
        this.manufacturerDAO.update(manufacturer);
    }

    @Override
    @Transactional
    public void delete(Manufacturer manufacturer) {
        this.manufacturerDAO.delete(manufacturer);
    }
}
