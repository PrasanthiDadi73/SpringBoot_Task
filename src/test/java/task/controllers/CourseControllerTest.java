package task.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import task.entities.Course;
import task.services.CourseService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @MockBean
    CourseService courseService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void saveCourseTest(){
        Course course = new Course();
        course.setCourseCode("JAVA");
        course.setCourseName("Java course");
        course.setCourseDescription("java programming");

        when(courseService.addCourse(course)).thenReturn(course);
        try {
            mockMvc.perform(post("/courses/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(course)))
                    .andExpect(status().isOk()); // or the  appropriate status you expect
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception or re
            // throw it as a runtime exception
        }
    }

}