package com.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.dao.EmployeeRepository;
import com.employee.exception.EmployeeException;
import com.employee.exception.EmployeeRepositoryException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.service.impl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =EmployeeServiceImpl.class)
public class EmployeeServiceTest {

	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
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
	
	
	private List<Employee> getListOfEmployee(){
		List<Employee> list= new ArrayList<>();
		list.add(getEmployee());
		return list;
	}
	
	@Autowired
	EmployeeService employeeService;
	
	@MockBean
	EmployeeRepository employeeRepository;
	
	@Test
	public void saveEmployeeTest() throws EmployeeRepositoryException, EmployeeException {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		int empId = employeeService.saveEmployee(getEmployee());
		assertEquals(empId, getEmployee().getId());
	}
	
	@Test
	public void updateEmployeeTest() throws EmployeeRepositoryException, EmployeeException {
		Mockito.when(employeeRepository.updateEmployee(Mockito.any())).thenReturn(1);
		int empId = employeeService.updateEmployee(getEmployee());
		assertEquals(empId!=0, true);
	}
	
	@Test
	public void deleteEmployeeTest() throws EmployeeRepositoryException, EmployeeException {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		Mockito.when(employeeRepository.deleteEmployee(Mockito.anyInt())).thenReturn(1);
		int empId = employeeService.deleteEmployee(1);
		assertEquals(empId!=0, true);
	}
	
	@Test
	public void listEmployeeTest() throws EmployeeRepositoryException, EmployeeException {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		Mockito.when(employeeRepository.getEmployees()).thenReturn(getListOfEmployee());
		List<Employee> emp = employeeService.getListEmployee();
		assertEquals(emp.isEmpty(),false);
	}
	
}
