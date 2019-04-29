package com.employee.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.dao.impl.EmployeeRepositoryImpl;
import com.employee.model.Employee;
import com.employee.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@MockBean
	EmployeeServiceImpl employeeService;
	
	@MockBean
	EmployeeRepositoryImpl employeeRepository;
	
    private JacksonTester <Employee> jsonView;
	
	@Autowired
	public MockMvc mockMvc;
	
    @Autowired 
    private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
        JacksonTester.initFields(this, objectMapper);
	}
	
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
	
	private List<Employee> getListOfEmployee(){
		List<Employee> list= new ArrayList<>();
		list.add(getEmployee());
		return list;
	}
	
	@Test
	public void getEmployeesTest() throws Exception {
		when(employeeService.getListEmployee()).thenReturn(getListOfEmployee());
		final String employeeJSON = jsonView.write(getEmployee()).getJson();
		this.mockMvc.perform(get("/api/Employee/list")
				.content(employeeJSON)
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void saveEmployeeTest() throws Exception{
		when(employeeService.saveEmployee(Mockito.any())).thenReturn(1);
		final String employeeJSON = jsonView.write(getEmployee()).getJson();
		this.mockMvc.perform(post("/api/Employee/save")
				.content(employeeJSON)
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void updateEmployeeTest() throws Exception{
		when(employeeService.updateEmployee(Mockito.any())).thenReturn(1);
		final String employeeJSON = jsonView.write(getEmployee()).getJson();
		this.mockMvc.perform(put("/api/Employee/update/1")
				.content(employeeJSON)
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception{
		when(employeeService.deleteEmployee(Mockito.anyInt())).thenReturn(1);
		final String employeeJSON = jsonView.write(getEmployee()).getJson();
		this.mockMvc.perform(delete("/api/Employee/delete/1")
				.content(employeeJSON)
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk());
	}
	
	
}
