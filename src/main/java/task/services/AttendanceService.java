package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import task.entities.Attendance;
import task.repositories.AttendanceRepo;

public interface AttendanceService {
	List<Attendance> getAttendance();

	Attendance addAttendance(Attendance attendance);

    void deleteAttendance(int attendanceId);

    void updateAttendance(int attendanceId,Attendance updatedAttendance);
}

@Service
class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
    private AttendanceRepo attendanceRepo;

	@Override
    public List<Attendance> getAttendance() {
        try {
            List<Attendance> attendancesList = attendanceRepo.findAll();
            if (attendancesList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No attendances found");
            }
            return attendancesList;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

	@Override
    public Attendance addAttendance(Attendance attendance) {
        try {
            Optional<Attendance> existingAttendance = attendanceRepo.findById(attendance.getAttendanceId());
            if (existingAttendance.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Attendance Id Already Present");
            }
            return attendanceRepo.save(attendance);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

	@Override
    public void deleteAttendance(int attendanceId) {
        Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);
        try {
            if (attendance.isPresent())
                attendanceRepo.deleteById(attendanceId);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance Id Not Found!");

       } catch (Exception ex) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                  "An error occurred while processing the request: " + ex.getMessage());
      }
       
    }

	@Override
    public void updateAttendance(int attendanceId, Attendance newAttendance) {
        Optional<Attendance> existingAttendance = attendanceRepo.findById(attendanceId);
        try {
            if (existingAttendance.isPresent()) {
                Attendance attendance = existingAttendance.get();
                attendance.setAttendanceDate(newAttendance.getAttendanceDate());
                attendance.setCourseId(newAttendance.getCourseId());
                attendance.setId(newAttendance.getId());
                attendanceRepo.save(attendance);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance Id Not Found!");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An error occurred while processing the request: " + ex.getMessage());
        }
    }
}