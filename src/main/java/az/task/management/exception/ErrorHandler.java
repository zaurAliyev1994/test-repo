package az.task.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public RestErrorResponse handleValidationException(Exception ex) {
        log.error("Invalid argument message: {}", ex.getMessage());
        return new RestErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({TaskNotFoundException.class})
    public RestErrorResponse handleNotFound(Exception ex) {
        log.error("Record not found message: {}", ex.getMessage());
        return new RestErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage());
    }
}