package task.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import task.entities.Trainee;
import task.repositories.TraineeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeApplicationTest {

    @Mock
    private TraineeRepo traineeRepo;

    @InjectMocks
    private TraineeService traineeService;

//    @BeforeEach
//    public void init(){
//        traineeService=new TraineeService();
//    }

    @Test
    void testGetTrainees() {

        when(traineeRepo.findAll()).thenReturn(List.of(
                new Trainee(1,3,3),new Trainee(2,1,2)));

        List<Trainee> trainee = traineeService.getAllTrainees();


        assertEquals(2, trainee.size(),"size is differ");
        assertEquals(1,trainee.get(0).getTraineeId());
        assertEquals(2,trainee.get(1).getTraineeId());
    }

//    @Test
//    public void testGetAllTraineeDetails(){
//        int courseId = 3;
//
//        when(traineeRepo.findDetailsByCourseId(courseId)).thenReturn((List) new Trainee(1,2,3));
//
//       Trainee trainee = traineeService.getDetails(courseId);
//
//        // assertEquals(1, department.size());
//
//        assertEquals(3, trainee.getId());
//
//    }
@Test
void testAddNewTrainee() {

    Trainee trainee = new Trainee(1,2,1);

    // No need to mock the behavior of the repository
    when(traineeRepo.save(trainee)).thenReturn(trainee);

    // Act
    Trainee addedTrainee = traineeService.addTrainee(trainee);

    // Assert
    assertEquals(trainee, addedTrainee);
   verify(traineeRepo, times(1)).save(trainee);
}

//@Test
//void testDeleteTrainee(){
//        int traineeId=1;
//        Trainee trainee=new Trainee(1,1,3);
//        when(traineeRepo.findById(traineeId)).thenReturn(Optional.of(trainee));
//        assertDoesNotThrow(() -> traineeService.deleteTrainee(traineeId));
//        verify(traineeRepo, times(1)).deleteById(traineeId);
//    }
}
