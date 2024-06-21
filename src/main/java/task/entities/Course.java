package task.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="course")
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="course_code")
	private String courseCode;
	
	@Column(name="course_name")
	private String courseName;
	
	@Column(name="course_description")
	private String courseDescription;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course") 
	@JsonIgnore
	private List<Attendance> attendes = new ArrayList<Attendance>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course") 
	@JsonIgnore
	private List<Schedule> schedules = new ArrayList<Schedule>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course") 
	@JsonIgnore
	private List<Trainee> trainees = new ArrayList<Trainee>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course") 
	@JsonIgnore
	private List<Trainer> trainers = new ArrayList<Trainer>();
	
	

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public List<Attendance> getAttendes() {
		return attendes;
	}

	public void setAttendes(List<Attendance> attendes) {
		this.attendes = attendes;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<Trainee> trainees) {
		this.trainees = trainees;
	}

	public List<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(List<Trainer> trainers) {
		this.trainers = trainers;
	}

//	public Course(int courseId, String courseCode, String courseName, String courseDescription) {
//		this.courseId = courseId;
//		this.courseCode = courseCode;
//		this.courseName = courseName;
//		this.courseDescription = courseDescription;
//	}
}
