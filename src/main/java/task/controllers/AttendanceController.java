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
import task.entities.Attendance;
import task.services.AttendanceService;

@RestController
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;

	
	@Operation(summary = "Get attendance", description = "Retrieve all Attendance details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Attendance data retrieved successfully."),
			@ApiResponse(responseCode = "404", description = "No attendance details found")
	})
	@GetMapping("/attendance")
	@PreAuthorize("hasRole('ROLE_USER')")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAttendance();
    }

	@Operation(summary = "Add Attendance", description = "Add all Attendance details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Attendance details added successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/attendance/add")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }

	@Operation(summary = "Delete Attendance", description = "Deleted Attendance details for the given attendanceId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Attendance record deleted successfully."),
			@ApiResponse(responseCode = "404", description = "No such attendanceId found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/attendance/delete/{attendanceId}")
    public void deleteAttendance(@PathVariable int attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
    }

	@Operation(summary = "Update Attendance", description = "Updated Attendance details for the given attendanceId.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Attendance details updated successfully."),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "400", description = "Bad Request(Wrong JSON Format)")
	})
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/attendance/update/{attendanceId}")
    public void updateAttendance(@PathVariable int attendanceId, @RequestBody Attendance newAttendance) {
        attendanceService.updateAttendance(attendanceId, newAttendance);
    }
	
//    @GetMapping("/attendance/employee/{attendanceId")
//    public List<Object[]> getAttendanceEmployeeDetails(@PathVariable int attendanceId){
//    	attendanceService.getAttendanceAndEmployeeDetails(attendanceId);
//    }
	
}

