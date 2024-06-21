package task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import task.entities.Course;
import task.services.CourseService;

@RestController
public class CourseController {
	
	 @Autowired
	    private CourseService courseService;
	 	
	 	@Operation(summary = "Get all Courses", description = "Retrieve all Course details.")
		@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Courses retrieved successfully."),
				@ApiResponse(responseCode = "404", description = "No courses found")
		})
//		@PreAuthorize("hasRole('ROLE_USER')")
	    @GetMapping("/getAllCourses")
	    public List<Course> getAllCourses() {
	        return courseService.getAllCourses();
	    }
	 	
	 	@Operation(summary = "Add Course", description = "Add all Course details.")
		@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Courses added successfully."),
				@ApiResponse(responseCode = "404", description = "Not found"),
				@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
		})
	    @PostMapping("/courses/add")
	    public Course addCourse(@RequestBody Course course) {
	        return courseService.addCourse(course);
	    }
	    
	 	@Operation(summary = "Delete Course", description = "Deleted Course details for the given courseId.")
		@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Courses deleted successfully."),
				@ApiResponse(responseCode = "404", description = "No such courseId found"),
				@ApiResponse(responseCode = "500", description = "Internal Server Error")
		})
	    @DeleteMapping("/courses/delete/{courseId}")
	    public void deleteCourse(@PathVariable Integer courseId) {
	        courseService.deleteCourse(courseId);
	    }

	 	@Operation(summary = "Update Course", description = "Update Course details for the given courseId.")
		@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Courses updated successfully."),
				@ApiResponse(responseCode = "404", description = "Not found"),
				@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
		})
	    @PutMapping("/courses/update/{courseId}")
	    public void updateCourse(@PathVariable int courseId, @RequestBody Course updatedCourse) {
	        courseService.updateCourse(courseId, updatedCourse);
	    }
		

}
