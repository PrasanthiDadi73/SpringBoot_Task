package task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import task.entities.Trainee;

public interface TraineeRepo extends JpaRepository<Trainee,Integer> {
	
	//@Query("Select t.courseId,t.course.courseName,t.traineeId from Trainee t where t.courseId=:courseId")
	@Query("Select new task.dto.TraineeCourseDTO(t.courseId,t.course.courseName,t.traineeId) from Trainee t where t.courseId=:courseId")
	List findDetailsByCourseId(@Param("courseId") int courseId);
	
	//@Query("SELECT t.course.courseId, t.course.courseName, t.traineeId, tr.trainerId FROM Trainee t JOIN t.course c JOIN c.trainers tr WHERE c.courseId = :courseId")
	@Query("SELECT new task.dto.DetailsBtCourseDTO2(t.course.courseId, t.course.courseName, t.traineeId, tr.trainerId) FROM Trainee t JOIN t.course c JOIN c.trainers tr WHERE c.courseId = :courseId")
	List<Object[]> findAllDetailsByCourseId(@Param("courseId") int courseId);

	
	@Query("SELECT new task.dto.DetailsDTO(c.courseId, c.courseName, tr.trainerId, t.traineeId, s.scheduleTime) " +
	           "FROM Course c " +
	           "JOIN c.trainers tr " +
	           "JOIN c.trainees t " +
	           "JOIN c.schedules s " +
	           "WHERE c.courseId = :courseId")
	//@Query("Select t.courseId,t.course.courseName,tr.trainerId,t.traineeId,s.scheduleTime from Trainee t JOIN t.course c JOIN c.trainers tr JOIN c.schedules s WHERE c.courseId=:courseId")
	List<Object[]> findAllTrainerAndCourseAndTimeDetails(@Param("courseId") int courseId);
	
}
