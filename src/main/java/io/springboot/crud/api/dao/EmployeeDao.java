package io.springboot.crud.api.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.springboot.crud.api.model.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
	public List<Employee> findByid(int id);
}
