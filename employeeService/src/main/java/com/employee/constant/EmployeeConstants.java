package com.employee.constant;

public class EmployeeConstants {
    public static final String INSERT_QUERY = "insert into employee (firstName, lastName,gender,dob,department) values (:firstName, :lastName,:gender,:dob,:department)";
    public static final String UPDATE_QUERY = "update employee set firstName = :firstName,lastName=:lastName,gender= :gender,dob= :dob,department=:department where  id = :id";
    public static final String DELETE_QUERY = "delete from employee where id = :id";
    public static final String SELECT_QUERY = "select id,firstName, lastName,gender,dob,department from employee order by firstName asc";
    public static final String SELECT_QUERY_BY_ID = "select id,firstName, lastName,gender,dob,department from  employee where id=:id";
    public static final String SELECT_QUERY_BY_NAME = "select id,firstName, lastName,gender,dob,department from  employee where firstName=:firstName";

    public static final String EMP_FIRSTNAME="firstName";
    public static final String EMP_LASTNAME = "lastName";
    public static final String EMP_ID = "id";
    public static final String EMP_DEPARTMENT = "department";
    public static final String EMP_GENDER ="gender";
    public static final String EMP_DOB = "dob";
    
	public static final String ERROR = "error";
	public static final String INFO = "info";
    
    private EmployeeConstants() {
    	//not allowed to create object outside
    }
}
