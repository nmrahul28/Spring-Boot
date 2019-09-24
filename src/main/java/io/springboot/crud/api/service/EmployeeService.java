package io.springboot.crud.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.springboot.crud.api.dao.EmployeeDao;
import io.springboot.crud.api.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employee_dao;
	
	public void addEmployee(List<Employee> employee) {
		employee_dao.saveAll(employee);
		System.out.print("hii");
	}
	
	public List<Employee> getEmployee(){
		return (List<Employee>)employee_dao.findAll();
	}
	
	public List<Employee> getEmployeeById(Integer id){
		return (List<Employee>) employee_dao.findByid(id);
	}
	
	public void updateEmployee(List<Employee> employee, int id) {
		employee_dao.saveAll(employee);
	}
	
	public void deleteEmployee(int id) {
		employee_dao.deleteById(id);
	}
}
