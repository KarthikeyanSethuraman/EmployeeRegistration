package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping(path="api/")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("Employee/save")
	public void createEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
	}
	
	@GetMapping("Employee/list")
	public List<Employee> listEmployee(){
		return employeeService.getListEmployee();
	} 
	
	@PutMapping("Employee/update/{id}")
	public void updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
		employee.setId(id);
		employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("Employee/delete/{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}
}
