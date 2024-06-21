package task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException course){

        CourseException ex=new CourseException(
                course.getMessage(),
                course.getCause(),
                 HttpStatus.NOT_FOUND
        );


    return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);

    }
}
