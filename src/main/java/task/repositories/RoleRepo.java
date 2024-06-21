package task.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Set<Role> findByNameIn(List<String> roleNames);
}
