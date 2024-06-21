package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import task.entities.Employee;
import task.repositories.EmployeeRepo;

public interface EmployeeService {
	List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    void deleteEmployee(Integer id);

    void updateEmployee(int id,Employee updatedEmployee);
}

@Service
class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Override
    public List<Employee> getAllEmployees() {
        try {
            List<Employee> employeesList = employeeRepo.findAll();
            if (employeesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found");
            }
            return employeesList;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    
    @Override
    public Employee addEmployee(Employee employee) {
        try {
            Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
            if (existingEmployee.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee Id Already Present");
            }
            return employeeRepo.save(employee);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
//    @Override
//    public Employee addEmployee(Employee employee) {
//        if (employeeRepo.existsById(employee.getId())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee with this Id Already Present");
//        }
//        return employeeRepo.save(employee);
//    }
    
    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        try {
            if (employee.isPresent())
                employeeRepo.deleteById(id);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id Not Found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An error occurred while processing the request: " + ex.getMessage());
        }
    }
//    @Override
//    public void deleteEmployee(Integer id) {
//        if (employeeRepo.existsById(id)) {
//            employeeRepo.deleteById(id);
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id Not Found!");
//        }
//    }
    
    @Override
    public void updateEmployee(int id, Employee newEmployee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(id);
        try {
            if (existingEmployee.isPresent()) {
                Employee employee = existingEmployee.get();
                employee.setEmployeeId(newEmployee.getEmployeeId());
                employee.setEmployeeName(newEmployee.getEmployeeName());
                employee.setEmailId(newEmployee.getEmailId());
                employee.setMobileNumber(newEmployee.getMobileNumber());
                employeeRepo.save(employee);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Id Not Found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An error occurred while processing the request: " + ex.getMessage());
        }
    }
}
