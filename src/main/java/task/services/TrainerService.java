package task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import task.entities.Trainer;
import task.repositories.TrainerRepo;

@Service
public class TrainerService {
	
	@Autowired
    private TrainerRepo trainerRepo;

    public List<Trainer> getAllTrainers() {
        return trainerRepo.findAll();
    }

    public Trainer addTrainer(Trainer trainer) {
        if (trainerRepo.existsById(trainer.getTrainerId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainer Id Already Present");
        }
        return trainerRepo.save(trainer);
    }

    public void deleteTrainer(Integer trainerId) {
        if (trainerRepo.existsById(trainerId)) {
            trainerRepo.deleteById(trainerId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer Id Not Found!");
        }
    }

    public void updateTrainer(int trainerId, Trainer updatedTrainer) {
        Optional<Trainer> existingTrainer = trainerRepo.findById(trainerId);
        if (existingTrainer.isPresent()) {
            Trainer trainer = existingTrainer.get();
            trainer.setCourseId(updatedTrainer.getCourseId());
            trainer.setId(updatedTrainer.getId());
            trainerRepo.save(trainer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer Id Not Found!");
        }
    }
}
