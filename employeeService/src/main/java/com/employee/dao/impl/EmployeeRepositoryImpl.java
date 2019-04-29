package com.employee.dao.impl;

import static com.employee.constant.EmployeeConstants.DELETE_QUERY;
import static com.employee.constant.EmployeeConstants.EMP_DEPARTMENT;
import static com.employee.constant.EmployeeConstants.EMP_FIRSTNAME;
import static com.employee.constant.EmployeeConstants.EMP_ID;
import static com.employee.constant.EmployeeConstants.EMP_LASTNAME;
import static com.employee.constant.EmployeeConstants.EMP_DOB;
import static com.employee.constant.EmployeeConstants.EMP_GENDER;
import static com.employee.constant.EmployeeConstants.INSERT_QUERY;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY_BY_ID;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY_BY_NAME;
import static com.employee.constant.EmployeeConstants.UPDATE_QUERY;
import static com.employee.constant.EmployeeConstants.ERROR;
import static com.employee.constant.EmployeeConstants.INFO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.employee.dao.EmployeeRepository;
import com.employee.exception.EmployeeRepositoryException;
import com.employee.model.Employee;

@Repository("emoniRepostiory")
public class EmployeeRepositoryImpl implements EmployeeRepository{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);
	
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
    
    @Override
    public int saveEmployee(Employee employee) throws EmployeeRepositoryException {
		logger.info(INFO+": save Employee initiating");
    	int result = 0;
    	try {
        	KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource namedParameters = new MapSqlParameterSource()
            		.addValue(EMP_FIRSTNAME, employee.getFirstName())
            		.addValue(EMP_LASTNAME, employee.getLastName())
            		.addValue(EMP_DEPARTMENT, employee.getDepartment())
            		.addValue(EMP_GENDER, employee.getGender())
            		.addValue(EMP_DOB, employee.getDob());
        	namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters,holder);
        	employee.setId(holder.getKey().intValue());
        	result = employee.getId();
    	}catch(Exception e) {
			logger.debug(ERROR+": "+e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
    	}
		logger.info(INFO+": Employee data inserted successfully");
        return result;
    }
    
    @Override
    public Employee findByEmployeeId(int id) throws EmployeeRepositoryException {
		logger.info(INFO+": Fetching employee data by employee id");
    	Employee employee = null;
    	try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
            		.addValue(EMP_ID, id);
        	employee = namedParameterJdbcTemplate.queryForObject(SELECT_QUERY_BY_ID, namedParameters, new EmployeeRoWMapper());
    	}catch(Exception e) {
			logger.debug(ERROR+": "+e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
    	}
		logger.info(INFO+": Fetched employee data");
    	return employee;

    }
    
    @Override
    public Employee findByEmployeeName(String firstName) throws EmployeeRepositoryException {
		logger.info(INFO+": Fetching employee data by employee firstName");
    	Employee employee = null;
    	try {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
            		.addValue(EMP_FIRSTNAME, firstName);
        	employee = namedParameterJdbcTemplate.queryForObject(SELECT_QUERY_BY_NAME, namedParameters, new EmployeeRoWMapper());
    	}catch(Exception e) {
			logger.debug(ERROR+": "+e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
    	}
		logger.info(INFO+": Fetched employee data");
    	return employee;
    }
    
    @Override
    public int updateEmployee(Employee employee) throws EmployeeRepositoryException {
		logger.info(INFO + ": update Employee initiating");
		int result = 0;
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue(EMP_ID, employee.getId())
					.addValue(EMP_FIRSTNAME, employee.getFirstName())
					.addValue(EMP_LASTNAME, employee.getLastName())
					.addValue(EMP_DEPARTMENT, employee.getDepartment())
					.addValue(EMP_GENDER, employee.getGender())
					.addValue(EMP_DOB, employee.getDob());
			result = namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
		} catch (Exception e) {
			logger.debug(ERROR + ": " + e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
		}
		logger.info(INFO + ": Employee data Updated successfully");
		return result;
	}
    
    @Override
    public int deleteEmployee(int employeeId) throws EmployeeRepositoryException {
		logger.info(INFO + ": delete Employee initiating");
    	int result = 0;
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue(EMP_ID, employeeId);
			result = namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
		}catch (Exception e) {
			logger.debug(ERROR + ": " + e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
		}
		logger.info(INFO + ": Employee data deleted successfully");
    	return result;
    }
    
    @Override
    public List<Employee> getEmployees() throws EmployeeRepositoryException{
		logger.info(INFO + ": fetching all employee data");

    	List<Employee> employees = new ArrayList<>();
    	try {
    		employees = namedParameterJdbcTemplate.query(SELECT_QUERY,new EmployeeRoWMapper());
    	}catch (Exception e) {
			logger.debug(ERROR + ": " + e.getMessage());
			throw new EmployeeRepositoryException(e.getMessage());
		}
		logger.info(INFO + ": fetched all employee data successfully");
    	return employees;
    }
}

class EmployeeRoWMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Employee employee = new Employee();
    	employee.setId(rs.getInt(EMP_ID));
    	employee.setDepartment(rs.getString(EMP_DEPARTMENT));
    	employee.setFirstName(rs.getString(EMP_FIRSTNAME));
    	employee.setLastName(rs.getString(EMP_LASTNAME));
    	employee.setGender(rs.getString(EMP_GENDER));
    	employee.setDob(rs.getString(EMP_DOB));
    	return employee;
    }
}