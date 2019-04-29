package com.employee.model;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String department;
	private String gender;
	private String dob;

	public int getId() {
		return id;
	}
	public void setId(int empId) {
		this.id = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gneder) {
		this.gender = gneder;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
