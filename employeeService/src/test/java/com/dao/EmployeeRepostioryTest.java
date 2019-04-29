package com.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.employee.EmployeeStarter;
import com.employee.dao.EmployeeRepository;
import com.employee.exception.EmployeeRepositoryException;
import com.employee.model.Employee;

@RunWith(SpringRunner.class)
@Transactional //It will rollback db operation
@SpringBootTest(classes = EmployeeStarter.class)
public class EmployeeRepostioryTest {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setDepartment("Software Developement");
		employee.setFirstName("test");
		employee.setLastName("test");
		employee.setGender("Male");
		employee.setDob(getCurrentDate());
		return employee;
	}
	
	private String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}
	
	@Test
	public void createEmployeeTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		int id = employeeRepository.saveEmployee(employee);
		Employee emp= employeeRepository.findByEmployeeId(id);
		assertEquals(emp.getFirstName(),employee.getFirstName());
		assertEquals(emp.getLastName(),employee.getLastName());
	}
	
	@Test
	public void findByFirstNameTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		employeeRepository.saveEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeName("test");
		assertEquals(employee.getFirstName(), emp.getFirstName());
	}
	
	@Test
	public void findByIdTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeId(empId);
		assertEquals(employee.getFirstName(), emp.getFirstName());
	}
	
	@Test
	public void updateEmployeeTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		employee.setId(empId);
		employee.setLastName("testtest");
		employeeRepository.updateEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeId(empId);
		assertEquals(employee.getLastName(), emp.getLastName());
	}
	
	@Test
	public void getEmployeesTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		employeeRepository.saveEmployee(employee);
		List<Employee> emp = employeeRepository.getEmployees();
		assertEquals(emp.isEmpty(), false);
	}
	
	@Test
	public void deleteEmployeeTest() throws EmployeeRepositoryException {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		int result = employeeRepository.deleteEmployee(empId);
		assertEquals(result!=0, true);
	}
}
