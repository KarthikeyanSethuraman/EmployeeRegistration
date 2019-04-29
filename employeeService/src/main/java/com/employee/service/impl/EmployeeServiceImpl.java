package com.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeRepository;
import com.employee.exception.EmployeeException;
import com.employee.exception.EmployeeRepositoryException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public int saveEmployee(Employee employee) throws EmployeeException{
		int result =0;
		try {
			result = employeeRepository.saveEmployee(employee);
		}catch (EmployeeRepositoryException e) {
			logger.debug(e.getMessage());
			throw new EmployeeException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<Employee> getListEmployee() throws EmployeeException{
		List<Employee> result =new ArrayList<>();
		try {
			result = employeeRepository.getEmployees();
		}catch (EmployeeRepositoryException e) {
			logger.debug(e.getMessage());
			throw new EmployeeException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public int updateEmployee(Employee employee) throws EmployeeException{
		int result =0;
		try {
			result = employeeRepository.updateEmployee(employee);
		}catch (EmployeeRepositoryException e) {
			logger.debug(e.getMessage());
			throw new EmployeeException(e.getMessage());
		}
		return result;
	}
	
	@Override
	public int deleteEmployee(int id) throws EmployeeException{
		int result =0;
		try {
			result = employeeRepository.deleteEmployee(id);
		}catch (EmployeeRepositoryException e) {
			logger.debug(e.getMessage());
			throw new EmployeeException(e.getMessage());
		}
		return result;
	}
}
