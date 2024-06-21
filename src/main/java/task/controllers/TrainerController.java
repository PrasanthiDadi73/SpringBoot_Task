package task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import task.entities.Trainer;
import task.services.TrainerService;

@RestController
public class TrainerController {
	@Autowired
    private TrainerService trainerService;
	
//	@GetMapping("/hi")
//	public String welcome() {
//	return "hi";
//	}

	@Operation(summary = "Get all Trainers", description = "Retrieve all Trainer details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainer data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No Trainer found")
	})
    @GetMapping("/getAllTrainers")
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

	@Operation(summary = "Add Trainer", description = "Add all Trainer details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainer added successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PostMapping("/trainer/add")
    public Trainer addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

	@Operation(summary = "Delete Trainer", description = "Deleted Trainer details for the given trainerId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainers deleted successfully."),
			@ApiResponse(responseCode = "404", description = "No such trainerId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
    @DeleteMapping("/trainer/delete/{trainerId}")
    public void deleteTrainer(@PathVariable Integer trainerId) {
        trainerService.deleteTrainer(trainerId);
    }

	@Operation(summary = "Update Trainer", description = "Update trainer details for the given trainerId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Trainer details updated successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PutMapping("/trainer/update/{trainerId}")
    public void updateTrainer(@PathVariable int trainerId, @RequestBody Trainer updatedTrainer) {
        trainerService.updateTrainer(trainerId, updatedTrainer);
    }
}
