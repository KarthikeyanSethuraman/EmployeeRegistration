package com.employee.dao;

import java.util.List;

import com.employee.exception.EmployeeRepositoryException;
import com.employee.model.Employee;

public interface EmployeeRepository {
    public int saveEmployee(Employee employee) throws EmployeeRepositoryException ;
    public Employee findByEmployeeId(int id) throws EmployeeRepositoryException ;
    public Employee findByEmployeeName(String firstName) throws EmployeeRepositoryException ;
	public int updateEmployee(Employee employee) throws EmployeeRepositoryException ;
    public int deleteEmployee(int employeeId) throws EmployeeRepositoryException ;
    public List<Employee> getEmployees() throws EmployeeRepositoryException;
}
