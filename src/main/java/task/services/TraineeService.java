package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import task.entities.Trainee;
import task.exceptions.CourseNotFoundException;
import task.exceptions.TraineeAlreadyPresentException;
import task.repositories.TraineeRepo;

@Service
public class TraineeService {
	
	@Autowired
	private TraineeRepo traineeRepo;
	

    public List<Trainee> getAllTrainees() {
        return traineeRepo.findAll();
    }

    public Trainee addTrainee(Trainee trainee) {
        if (traineeRepo.existsById(trainee.getTraineeId())) {
            throw new TraineeAlreadyPresentException("Trainee Id Already Present");
        }
        return traineeRepo.save(trainee);
    }

    public void deleteTrainee(Integer traineeId) {
        if (traineeRepo.existsById(traineeId)) {
            traineeRepo.deleteById(traineeId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainee Id Not Found!");
        }
    }

    public void updateTrainee(int traineeId, Trainee updatedTrainee) {
        Optional<Trainee> existingTrainee = traineeRepo.findById(traineeId);
        if (existingTrainee.isPresent()) {
            Trainee trainee = existingTrainee.get();
            trainee.setCourseId(updatedTrainee.getCourseId());
            trainee.setId(updatedTrainee.getId());
            traineeRepo.save(trainee);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainee Id Not Found!");
        }
    }
	
	public List<Trainee> getDetails(int courseId){
		var list=traineeRepo.findDetailsByCourseId(courseId);
		if(list.isEmpty()){
			throw new CourseNotFoundException("No such course Id found for the Trainee");
		}
		return list;
	}
	
	public List<Object[]> getAllTraineeDetails(int courseId){
		var list=traineeRepo.findAllDetailsByCourseId(courseId);
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Course found" + courseId);
		}
		return list;
		}
	
	public List<Object[]> getAllTrainerAndCourseAndTimeDetails(int courseId){
		var list=traineeRepo.findAllTrainerAndCourseAndTimeDetails(courseId);
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No Course found" + courseId);
		}
		return list;
	}
}


