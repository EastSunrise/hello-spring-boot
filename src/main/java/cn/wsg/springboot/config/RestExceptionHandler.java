package cn.wsg.springboot.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions thrown from {@link org.springframework.web.bind.annotation.RestController}.
 *
 * @author Kingen
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
