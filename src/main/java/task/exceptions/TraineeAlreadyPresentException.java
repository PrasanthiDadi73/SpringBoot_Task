package task.exceptions;

public class TraineeAlreadyPresentException extends RuntimeException{

    public TraineeAlreadyPresentException(String message) {
        super(message);
    }

    public TraineeAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
