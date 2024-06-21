package task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

}
