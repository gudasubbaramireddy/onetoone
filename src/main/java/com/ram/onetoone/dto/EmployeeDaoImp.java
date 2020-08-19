package com.ram.onetoone.dto;

import com.ram.onetoone.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDaoImp implements EmployeeDao{

    //define fields for entity manager
    private EntityManager entityManager;

    //setup constuctor injection

    @Autowired
    public EmployeeDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        //get the current hibernet session
        Session currentSession=entityManager.unwrap(Session.class);

        //create a query
        Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);

        //execute query and get resultset
        List<Employee> employees=theQuery.getResultList();

        //return results
        return employees;
    }
}
