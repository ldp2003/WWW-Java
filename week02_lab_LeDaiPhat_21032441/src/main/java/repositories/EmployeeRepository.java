package repositories;


import enums.EmployeeStatus;
import jakarta.persistence.*;
import models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertEmp(Employee employee) {
        try {
            trans.begin();
            em.persist(employee);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public void setStatus(Employee employee, EmployeeStatus status) {
        employee.setStatus(status);
    }

    public void update(Employee employee) {
        try {
            trans.begin();
            em.merge(employee);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public Employee findbyId(long id) {
        return em.createNamedQuery("Employee.findById", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Employee> getAllEmp() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .setParameter("Status", EmployeeStatus.ACTIVE)
                .getResultList();
    }

}

