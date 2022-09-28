package com.tilmeez.cruddemo.dao;

import com.tilmeez.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define field for entityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Employee> finalAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Employee> theQuery =
                currentSession.createQuery("from Employee ", Employee.class);

        // execute the query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }
}
