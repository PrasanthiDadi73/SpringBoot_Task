package task.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import task.entities.Trainee;
import task.responses.TraineeResponseHandler;
import task.services.TraineeService;

@RestController
public class TraineeController {

	private static  final Logger log= LoggerFactory.getLogger(TraineeController.class);
	
	@Autowired
	private TraineeService traineeService;
	
	@Operation(summary = "Get all Trainees", description = "Retrieve all Trainee details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainee data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No Trainees found")
	})
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getAllTrainees")
    public ResponseEntity<Object> getAllTrainees() {
        log.info("Trainee Details succesfully returned");
		log.debug("Debugging");
//		return traineeService.getAllTrainees();
		return TraineeResponseHandler.responseBuilder("Trainee Details", HttpStatus.OK, traineeService.getAllTrainees());
    }

	@Operation(summary = "Add Trainee", description = "Add all Trainee details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainee added successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PostMapping("/trainee/add")
    public ResponseEntity<Object> addTrainee(@RequestBody Trainee trainee) {
		log.debug("Adding Trainee details");
        return TraineeResponseHandler.responseBuilder("Adding Trainee successfully",HttpStatus.OK, traineeService.addTrainee(trainee));
    }

	@Operation(summary = "Delete Trainee", description = "Deleted Trainee details for the given traineeId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainees deleted successfully."),
			@ApiResponse(responseCode = "404", description = "No such traineeId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
    @DeleteMapping("/trainee/delete/{traineeId}")
    public void deleteTrainee(@PathVariable Integer traineeId) {
        traineeService.deleteTrainee(traineeId);
    }

	@Operation(summary = "Update Trainee", description = "Update trainee details for the given traineeId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainees updated successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PutMapping("/trainee/update/{traineeId}")
    public void updateTrainee(@PathVariable int traineeId, @RequestBody Trainee updatedTrainee) {
        traineeService.updateTrainee(traineeId, updatedTrainee);
    }
	
	@Operation(summary = "Get Trainee and Course", description = "Retrieve Trainee and Course details for the given courseId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No such courseId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getDetails/{courseId}")
	public List<Trainee> getAllDetails(@PathVariable int courseId) {
        return traineeService.getDetails(courseId);
    }
	
	@Operation(summary = "Get Trainer and Course details", description = "Retrieve Trainer and Course details for the given courseId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No such courseId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/getTrainerAndCourseDetails/{courseId}")
	public List<Object[]> getTraineeDetails(@PathVariable int courseId){
		return traineeService.getAllTraineeDetails(courseId);
	}
	
	@Operation(summary = "Get Trainer and Course and Schedule time", description = "Retrieve Trainer,Schedule time and Course details for the given courseId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No such courseId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping("/getTrainerAndCourseAndTimeDetails/{courseId}")
	public List<Object[]> getTraineeAndScheduleController(@PathVariable int courseId){
		return traineeService.getAllTrainerAndCourseAndTimeDetails(courseId);
	}
}
