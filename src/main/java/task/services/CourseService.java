package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import task.entities.Course;
import task.repositories.CourseRepo;

public interface CourseService {
	List<Course> getAllCourses();

    Course addCourse(Course course);

    void deleteCourse(Integer courseId);

    void updateCourse(int courseId, Course updatedCourse);
}

@Service
class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;



    @Override
    public List<Course> getAllCourses() {
        var list= courseRepo.findAll();
        if(list.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course table is Empty!");
        }
        return list;
    }

    @Override
    public Course addCourse(Course course) {
        if (courseRepo.existsById(course.getCourseId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course Id Already Present");
        }
        return courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        if (courseRepo.existsById(courseId)) {
            courseRepo.deleteById(courseId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Id Not Found!");
        }
    }

    @Override
    public void updateCourse(int courseId, Course updatedCourse) {
        Optional<Course> existingCourse = courseRepo.findById(courseId);
        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setCourseCode(updatedCourse.getCourseCode());
            course.setCourseDescription(updatedCourse.getCourseDescription());
            course.setCourseName(updatedCourse.getCourseName());
            courseRepo.save(course);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Id Not Found!");
        }
    }
}
