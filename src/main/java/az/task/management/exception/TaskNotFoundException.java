package az.task.management.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
