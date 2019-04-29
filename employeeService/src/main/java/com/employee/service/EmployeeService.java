package com.employee.service;

import java.util.List;

import com.employee.exception.EmployeeException;
import com.employee.model.Employee;

public interface EmployeeService {

	public int saveEmployee(Employee employee) throws EmployeeException;
	public List<Employee> getListEmployee() throws EmployeeException;
	public int updateEmployee(Employee employee) throws EmployeeException;
	public int deleteEmployee(int id) throws EmployeeException;
	
}
