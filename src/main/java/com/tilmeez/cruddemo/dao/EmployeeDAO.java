package com.tilmeez.cruddemo.dao;

import com.tilmeez.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> finalAll();

    public Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deleteById(int theId);

}
