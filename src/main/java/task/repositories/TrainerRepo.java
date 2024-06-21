package task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer,Integer> {

}
