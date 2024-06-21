package task.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import task.entities.Course;
import task.repositories.CourseRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CourseServiceUnitTest {

    @MockBean
    CourseRepo courseRepo;

    @Autowired
    private CourseService courseService; // as writing test cases for service layer.

//    @Test
//    void getAllCourses(){
//        Course course = new Course();
//        course.setCourseCode("Jwt");
//        course.setCourseName("Jwt course");
//        course.setCourseDescription("Jwt token");
//
//       when(courseRepo.findAll()).thenReturn((List<Course>) course);
//       assertEquals(2,courseService.getAllCourses().size());
//
//
//    }

    @Test
    @Rollback
    void addCourse() {
        Course course = new Course();
        course.setCourseCode("Junit203");
        course.setCourseName("Junit course");
        course.setCourseDescription("Junit and Mockito");

        // When
//        Course savedCourse = courseRepo.save(course);
        when(courseRepo.save(course)).thenReturn(course);    // as we mock the repo the data is not added into db.
        assertEquals(course,courseService.addCourse(course));
    }

//    @Test
//    @Rollback
//    void deleteCourse() {
//
//        Course course1 = new Course();
//        course1.setCourseCode("Junit203");
//        course1.setCourseName("Junit course");
//        course1.setCourseDescription("Junit and Mockito");
//        courseService.deleteCourse(course1.getCourseCode());
//        verify(courseRepo,times(1)).delete(course1);
//
//    }

    @Test
    void updateCourse() {
    }
}