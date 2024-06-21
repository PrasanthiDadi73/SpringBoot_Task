package task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddingTraineeHandler {

    @ExceptionHandler(value = TraineeAlreadyPresentException.class)
    public ResponseEntity<Object> addingTraineeExceptionHandler(TraineeAlreadyPresentException trainee){
        AddTraineeException ate=new AddTraineeException(
                trainee.getMessage(),
                trainee.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(ate, HttpStatus.BAD_REQUEST);
    }

}
