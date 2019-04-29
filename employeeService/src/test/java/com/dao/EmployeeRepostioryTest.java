package com.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.employee.EmployeeStarter;
import com.employee.dao.EmployeeRepository;
import com.employee.model.Employee;

@RunWith(SpringRunner.class)
@Transactional //It will rollback db operation
@SpringBootTest(classes = EmployeeStarter.class)
public class EmployeeRepostioryTest {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmail("test@gmail.com");
		employee.setEmpCode("123");
		employee.setFirstName("test");
		employee.setLastName("test");
		employee.setMobileNumber("1234567890");
		return employee;
	}
	
	@Test
	public void createEmployeeTest() {
		Employee employee = getEmployee();
		int id = employeeRepository.saveEmployee(employee);
		Employee emp= employeeRepository.findByEmployeeId(id);
		assertEquals(emp.getEmail(),employee.getEmail());
		assertEquals(emp.getFirstName(),employee.getFirstName());
		assertEquals(emp.getLastName(),employee.getLastName());
	}
	
	@Test
	public void findByFirstNameTest() {
		Employee employee = getEmployee();
		employeeRepository.saveEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeName("test");
		assertEquals(employee.getFirstName(), emp.getFirstName());
	}
	
	@Test
	public void findByIdTest() {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeId(empId);
		assertEquals(employee.getFirstName(), emp.getFirstName());
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		employee.setId(empId);
		employee.setLastName("testtest");
		employeeRepository.updateEmployee(employee);
		Employee emp = employeeRepository.findByEmployeeId(empId);
		assertEquals(employee.getLastName(), emp.getLastName());
	}
	
	@Test
	public void getEmployeesTest() {
		Employee employee = getEmployee();
		employeeRepository.saveEmployee(employee);
		List<Employee> emp = employeeRepository.getEmployees();
		assertEquals(emp.isEmpty(), false);
	}
	
	@Test
	public void deleteEmployeeTest() {
		Employee employee = getEmployee();
		int empId = employeeRepository.saveEmployee(employee);
		int result = employeeRepository.deleteEmployee(empId);
		assertEquals(result!=0, true);
	}
}
