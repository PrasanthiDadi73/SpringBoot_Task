package task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import task.entities.Schedule;
import task.services.ScheduleService;

@RestController
public class ScheduleController {
	
	@Autowired
    private ScheduleService scheduleService;

	@Operation(summary = "Get all Schedules", description = "Retrieve all Schedule details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Schedule records retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No schedule found")
	})
    @GetMapping("/schedule")
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @Operation(summary = "Add Schedule", description = "Add all Schedule details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Schedule added successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PostMapping("/schedule/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule addSchedule(@RequestBody Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @Operation(summary = "Delete Schedule", description = "Deleted Schedule details for the given Id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Schedule records deleted successfully."),
			@ApiResponse(responseCode = "404", description = "No such Id found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
    @DeleteMapping("/schedule/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
    }

    @Operation(summary = "Update Schedule", description = "Update Schedule details for the given Id.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Schedule details updated successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
    @PutMapping("/schedule/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSchedule(@PathVariable int id, @RequestBody Schedule newSchedule) {
        scheduleService.updateSchedule(id, newSchedule);
    }
}
