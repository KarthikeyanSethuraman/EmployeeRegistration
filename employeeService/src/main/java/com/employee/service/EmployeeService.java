package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeRepository;
import com.employee.model.Employee;

@Service("employeeService")
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public int saveEmployee(Employee employee) {
		return employeeRepository.saveEmployee(employee);
	}
	
	public List<Employee> getListEmployee(){
		return employeeRepository.getEmployees();
	}
	
	public int updateEmployee(Employee employee) {
		 return employeeRepository.updateEmployee(employee);
	}
	
	public int deleteEmployee(int id) {
		return employeeRepository.deleteEmployee(id);
	}
}
