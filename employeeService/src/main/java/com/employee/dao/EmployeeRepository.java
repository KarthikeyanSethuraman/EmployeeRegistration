package com.employee.dao;

import static com.employee.constant.EmployeeConstants.INSERT_QUERY;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY_BY_ID;
import static com.employee.constant.EmployeeConstants.SELECT_QUERY_BY_NAME;
import static com.employee.constant.EmployeeConstants.UPDATE_QUERY;
import static com.employee.constant.EmployeeConstants.DELETE_QUERY;
import static com.employee.constant.EmployeeConstants.EMP_FIRSTNAME;
import static com.employee.constant.EmployeeConstants.EMP_LASTNAME;
import static com.employee.constant.EmployeeConstants.EMP_ID;
import static com.employee.constant.EmployeeConstants.EMP_MOBILE;
import static com.employee.constant.EmployeeConstants.EMP_CODE;
import static com.employee.constant.EmployeeConstants.EMP_EMAIL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository("emoniRepostiory")
public class EmployeeRepository {
	
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
    
    public int saveEmployee(Employee employee) {
    	KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        		.addValue(EMP_FIRSTNAME, employee.getFirstName())
        		.addValue(EMP_LASTNAME, employee.getLastName())
        		.addValue(EMP_CODE, employee.getEmpCode())
        		.addValue(EMP_MOBILE, employee.getMobileNumber())
        		.addValue(EMP_EMAIL, employee.getEmail());
    	namedParameterJdbcTemplate.update(INSERT_QUERY, namedParameters,holder);
    	employee.setId(holder.getKey().intValue());
        return employee.getId();
    }
    
    public Employee findByEmployeeId(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        		.addValue(EMP_ID, id);
    	return namedParameterJdbcTemplate.queryForObject(SELECT_QUERY_BY_ID, namedParameters, new EmployeeRoWMapper());
    }
    
    public Employee findByEmployeeName(String firstName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        		.addValue(EMP_FIRSTNAME, firstName);
    	return namedParameterJdbcTemplate.queryForObject(SELECT_QUERY_BY_NAME, namedParameters, new EmployeeRoWMapper());
    }
    
    public int updateEmployee(Employee employee) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        		.addValue(EMP_ID, employee.getId())
        		.addValue(EMP_FIRSTNAME, employee.getFirstName())
        		.addValue(EMP_LASTNAME, employee.getLastName())
        		.addValue(EMP_CODE, employee.getEmpCode())
        		.addValue(EMP_MOBILE, employee.getMobileNumber())
        		.addValue(EMP_EMAIL, employee.getEmail());
        return namedParameterJdbcTemplate.update(UPDATE_QUERY, namedParameters);
    }
    
    public int deleteEmployee(int employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
        		.addValue("id", employeeId);
        return namedParameterJdbcTemplate.update(DELETE_QUERY, namedParameters);
    }
    
    public List<Employee> getEmployees(){
    	return namedParameterJdbcTemplate.query(SELECT_QUERY,new EmployeeRoWMapper());
    }
}

class EmployeeRoWMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Employee employee = new Employee();
    	employee.setId(rs.getInt(EMP_ID));
    	employee.setEmpCode(rs.getString(EMP_CODE));
    	employee.setEmail(rs.getString(EMP_EMAIL));
    	employee.setFirstName(rs.getString(EMP_FIRSTNAME));
    	employee.setLastName(rs.getString(EMP_LASTNAME));
    	employee.setMobileNumber(rs.getString(EMP_MOBILE));
    	return employee;
    }
}