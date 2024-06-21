package task.dto;

public class DetailsBtCourseDTO2 {

	private Integer courseId;
	
	private String courseName;
	
	private Integer traineeId;
	
	private Integer trainerId;

	public DetailsBtCourseDTO2(Integer courseId, String courseName, Integer traineeId, Integer trainerId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.traineeId = traineeId;
		this.trainerId = trainerId;
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

	public Integer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
	
	
}
