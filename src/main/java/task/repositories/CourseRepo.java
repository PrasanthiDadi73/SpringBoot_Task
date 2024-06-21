package task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import task.entities.Course;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,Integer> {

    Optional<Course> findByCourseCode(String courseCode);
}
