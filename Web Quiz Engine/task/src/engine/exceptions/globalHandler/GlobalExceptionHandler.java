package engine.exceptions.globalHandler;

import engine.exceptions.ExceptionDTO;
import engine.exceptions.QuizNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDTO> notFound(QuizNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(exception.getMessage()));
    }
}
