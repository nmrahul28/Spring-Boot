package io.springboot.crud.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.springboot.crud.api.model.Employee;
import io.springboot.crud.api.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employee_service;

	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<?> addEmployee(RequestEntity<List<Employee>> requestEntity) {
		if(!requestEntity.getBody().isEmpty()) {
			employee_service.addEmployee(requestEntity.getBody());
			return new ResponseEntity<>("Employee Added success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Invalid Body", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get")
	public ResponseEntity<List<Employee>> getEmployee(){
		List<Employee> list = employee_service.getEmployee();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id){
		List<Employee> data = employee_service.getEmployeeById(id);
		if(!data.isEmpty()) {
			return new ResponseEntity<List<Employee>>(data, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Employee Found For Given Id", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable int id, RequestEntity<List<Employee>> requestEntity){
		if(getEmployeeById(id)!=null && !requestEntity.getBody().isEmpty()) {
			employee_service.updateEmployee(requestEntity.getBody(), id);
			return new ResponseEntity<>(requestEntity.getBody(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("invalid format", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		if(getEmployeeById(id)!=null) {
			employee_service.deleteEmployee(id);
			return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Data for given id", HttpStatus.NOT_FOUND);
		}
	}

}
