package task.dto;
import java.time.LocalTime;

public class DetailsDTO {

	private Integer courseId;
    private String courseName;
    private Integer trainerId;
    private Integer traineeId;
    private LocalTime scheduleTime;
    
	public DetailsDTO(Integer courseId, String courseName, Integer trainerId, Integer traineeId,
			LocalTime scheduleTime) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.trainerId = trainerId;
		this.traineeId = traineeId;
		this.scheduleTime = scheduleTime;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public LocalTime getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(LocalTime scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	
	
    
	
}
