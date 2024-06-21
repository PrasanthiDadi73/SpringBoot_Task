package task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import task.entities.Employee;
import task.services.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Get all Employees", description = "Retrieve all Employee details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No employees found") })
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@Operation(summary = "Add Employee", description = "Add all Employee details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees added successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)") })
	@PostMapping("/employees/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@Operation(summary = "Delete Employee", description = "Delete Employee details for the given Id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees deleted successfully."),
			@ApiResponse(responseCode = "404", description = "No such Id found for the Employee"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping("/employees/delete/{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}

	@Operation(summary = "Update Employee", description = "Update Employee details for the given Id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employees updated successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)") })
	@PutMapping("/employees/update/{id}")
	public void updateEmployee(@PathVariable int id, @RequestBody Employee newEmployee) {
		employeeService.updateEmployee(id, newEmployee);
	}
}
