package com.employee.exception;


public class EmployeeRepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeRepositoryException(String dataAccessException) {
		super(dataAccessException);
	}
}
