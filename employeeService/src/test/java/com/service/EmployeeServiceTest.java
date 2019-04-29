package com.service;

import static org.junit.Assert.assertEquals;

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
import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =EmployeeService.class)
public class EmployeeServiceTest {

	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmail("test@gmail.com");
		employee.setEmpCode("123");
		employee.setFirstName("test");
		employee.setLastName("test");
		employee.setMobileNumber("1234567890");
		employee.setId(1);
		return employee;
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
	public void saveEmployeeTest() {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		int empId = employeeService.saveEmployee(getEmployee());
		assertEquals(empId, getEmployee().getId());
	}
	
	@Test
	public void updateEmployeeTest() {
		Mockito.when(employeeRepository.updateEmployee(Mockito.any())).thenReturn(1);
		int empId = employeeService.updateEmployee(getEmployee());
		assertEquals(empId!=0, true);
	}
	
	@Test
	public void deleteEmployeeTest() {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		Mockito.when(employeeRepository.deleteEmployee(Mockito.anyInt())).thenReturn(1);
		int empId = employeeService.deleteEmployee(1);
		assertEquals(empId!=0, true);
	}
	
	@Test
	public void listEmployeeTest() {
		Mockito.when(employeeRepository.saveEmployee(Mockito.any())).thenReturn(1);
		Mockito.when(employeeRepository.getEmployees()).thenReturn(getListOfEmployee());
		List<Employee> emp = employeeService.getListEmployee();
		assertEquals(emp.isEmpty(),false);
	}
	
}
