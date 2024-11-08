package services;



import enums.EmployeeStatus;
import models.Employee;
import repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService() {
        repository = new EmployeeRepository();
    }

    public void insertEmp(Employee employee) {
        repository.insertEmp(employee);
    }


    public Employee findById(long id) {
        return repository.findbyId(id);
    }

    public boolean delete(long id) {
       Employee em = findById(id);
        if (em!=null) {
            em.setStatus(EmployeeStatus.TERMINATED);
            return true;
        }
        return false;
    }

    public boolean activeEmp(long id) {
        Employee em = findById(id);
        if (em!=null) {
            em.setStatus(EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public List<Employee> getAll() {
        return repository.getAllEmp();
    }

}

