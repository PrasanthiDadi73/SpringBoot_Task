package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import task.entities.Schedule;
import task.repositories.ScheduleRepo;

//public interface ScheduleService {
//
//}

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    public List<Schedule> getAllSchedules() {
        try {
            List<Schedule> schedulesList = scheduleRepo.findAll();
            if (schedulesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No schedules found");
            }
            return schedulesList;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public Schedule addSchedule(Schedule schedule) {
        try {
            Optional<Schedule> existingSchedule = scheduleRepo.findById(schedule.getId());
            if (existingSchedule.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule Id Already Present");
            }
            return scheduleRepo.save(schedule);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public void deleteSchedule(int scheduleId) {
        Optional<Schedule> schedule = scheduleRepo.findById(scheduleId);
        try {
            if (schedule.isPresent())
                scheduleRepo.deleteById(scheduleId);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Id Not Found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An error occurred while processing the request: " + ex.getMessage());
        }
    }

    public void updateSchedule(int scheduleId, Schedule newSchedule) {
        Optional<Schedule> existingSchedule = scheduleRepo.findById(scheduleId);
        try {
            if (existingSchedule.isPresent()) {
                Schedule schedule = existingSchedule.get();
                schedule.setCourseId(newSchedule.getCourseId());
                schedule.setLocation(newSchedule.getLocation());
                schedule.setFromDate(newSchedule.getFromDate());
                schedule.setToDate(newSchedule.getToDate());
                schedule.setScheduleTime(newSchedule.getScheduleTime());
                scheduleRepo.save(schedule);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Id Not Found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "An error occurred while processing the request: " + ex.getMessage());
        }
    }
}
