package task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule,Integer> {

}
