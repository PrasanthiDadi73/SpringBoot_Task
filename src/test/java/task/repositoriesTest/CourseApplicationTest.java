package task.repositoriesTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import task.entities.Course;
import task.repositories.CourseRepo;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Customize test database behavior if needed
public class CourseApplicationTest {

    @Autowired
    private CourseRepo courseRepo;

    @Test
    @Order(1)f
    public void saveCourse() {
        // Given
        Course course = new Course();
        course.setCourseCode("JAVA");
        course.setCourseName("Java course");
        course.setCourseDescription("java programming");

        // When
        Course savedCourse = courseRepo.save(course);

        // Then
        Assertions.assertThat(savedCourse.getCourseId()).isNotNull();
        Assertions.assertThat(savedCourse.getCourseId()).isGreaterThan(0);
        // Add more assertions to validate the saved course details if needed
    }

    @Order(3)
    @Test
    public void getById() {
        Optional<Course> optionalCourse = courseRepo.findById(1);
        Course course = optionalCourse.orElseThrow(); // Get the Course object from Optional
        Assertions.assertThat(course.getCourseId()).isEqualTo(1);
        }

    @Order(2)
    @Test
    public void getListOfCourses(){
        List<Course> courses=courseRepo.findAll();
        Assertions.assertThat(courses.size()).isGreaterThan(2);
    }

    @Order(4)
    @Test
    public void updateCourse(){
       Course course=courseRepo.findById(1).get();
       course.setCourseCode("C");
       Course updatedCourse=courseRepo.save(course);
       Assertions.assertThat(updatedCourse.getCourseCode()).isEqualTo("C");
    }

    @Order(5)
    @Test
    public void deleteCourse(){

        Course course=courseRepo.findById(1).get();
        courseRepo.delete(course);

        Course c=null;

        Optional<Course> course1=courseRepo.findByCourseCode("C");

        if(course1.isPresent()){
            c=course1.get();
        }
        Assertions.assertThat(c).isNull();
    }
}
