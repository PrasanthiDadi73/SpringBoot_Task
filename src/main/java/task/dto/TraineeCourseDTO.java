package task.dto;

public class TraineeCourseDTO {
	
	private Integer courseId;
	
	private String courseName;
	
	private Integer traineeId;

	public TraineeCourseDTO(Integer courseId, String courseName, Integer traineeId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.traineeId = traineeId;
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

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	
	
}
