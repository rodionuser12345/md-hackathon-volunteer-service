package md.hackathon.springboot.volunteer_service_app.handlers;

import md.hackathon.springboot.volunteer_service_app.exception.VolunteerNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VolunteerNotFoundException.class)
    public ResponseEntity<String> handleVolunteerNotFoundException(VolunteerNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // This exception is thrown when there is a violation of a database constraint.
        // In a real-world application, you might want to parse the exception message to determine which constraint was violated and return a more user-friendly error message.
        return new ResponseEntity<>("Database error: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        // This exception is thrown when a method argument annotated with @Valid fails validation.
        // In this handler, we're returning a map of field names to error messages, so the client can display appropriate error messages next to each field.
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Other exception handlers can be added here
}