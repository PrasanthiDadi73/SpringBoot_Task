package task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="trainees")
@Entity
public class Trainee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trainee_id")
	private int traineeId;
	
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false) 
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false) 
	private Employee employee;

	public Trainee() {
	}

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Trainee(int traineeId,int courseId, int id) {
		this.traineeId=traineeId;
		this.courseId = courseId;
		this.id=id;
	}
}
