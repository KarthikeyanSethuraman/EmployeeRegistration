package com.employee.constant;

public class EmployeeConstants {
    public static final String INSERT_QUERY = "insert into employee (firstName, lastName,empCode,mobile,email) values (:firstName, :lastName,:empCode,:mobile,:email)";
    public static final String UPDATE_QUERY = "update employee set firstName = :firstName,lastName=:lastName,empCode= :empCode,mobile=:mobile,email=:email where  id = :id";
    public static final String DELETE_QUERY = "delete from employee where id = :id";
    public static final String SELECT_QUERY = "select id,firstName, lastName,empCode,mobile,email from employee order by firstName asc";
    public static final String SELECT_QUERY_BY_ID = "select id,firstName, lastName,empCode,mobile,email from employee where id=:id";
    public static final String SELECT_QUERY_BY_NAME = "select id,firstName, lastName,empCode,mobile,email from employee where firstName=:firstName";


    
    public static final String EMP_FIRSTNAME="firstName";
    public static final String EMP_LASTNAME = "lastName";
    public static final String EMP_ID = "id";
    public static final String EMP_CODE = "empCode";
    public static final String EMP_MOBILE = "mobile";
    public static final String EMP_EMAIL = "email";
    
    private EmployeeConstants() {
    	//not allowed to create object outside
    }
}
