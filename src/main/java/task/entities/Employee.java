package task.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="employee_id")
	private String employeeId;
	
	@Column(name="employee_name")
	private String employeeName;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee") 
	@JsonIgnore
	private List<Attendance> attendes = new ArrayList<Attendance>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee") 
	@JsonIgnore
	private List<Trainee> trainees = new ArrayList<Trainee>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee") 
	@JsonIgnore
	private List<Trainer> trainers = new ArrayList<Trainer>();
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Attendance> getAttendes() {
		return attendes;
	}

	public void setAttendes(List<Attendance> attendes) {
		this.attendes = attendes;
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

	

	
}
