package com.jumia.numbervalidator.dao;

import com.jumia.numbervalidator.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDAOHibernateImpl implements CustomerDAO{

    private EntityManager entityManager;

    // set up constructor injection for the entity manager
    @Autowired
    public CustomerDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        // get current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query =
                currentSession.createQuery("from Customer", Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();
        return customers;
    }
}
